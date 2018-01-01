package com.test;

import com.test.association.Employee12;
import com.test.session.Person;
import com.test.simple.Employee;
import com.test.util.HibernateUtil;
import org.hibernate.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class TestHibernate6FetchStrategies {
    private static SessionFactory sessionFactory;
    private static Session session = null;
    private Transaction transaction;

    @BeforeClass
    public static void beforeClass(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Before
    public void before(){
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void after(){
        session.close();
    }

    @AfterClass
    public static void afterClass(){
        sessionFactory.close();
    }

    @Test
    public void testGetMothodIsEagerLoadingIgnoreLazyType(){
        Employee12 employee12 = session.get(Employee12.class,new Long(2));
        assertThat(Hibernate.isInitialized(employee12), is(true));
        assertThat(Hibernate.isPropertyInitialized(employee12,"addresss"),is(true));
    }

    @Test
    public void testLoadMethodIsLazyLoading(){
        Employee12 employee12 = session.load(Employee12.class, new Long(2));
        assertThat(Hibernate.isInitialized(employee12), is(false));
        assertThat(Hibernate.isPropertyInitialized(employee12,"addresss"),is(false));

        employee12.getAddresss().size();
        transaction.commit();

        assertThat(Hibernate.isInitialized(employee12), is(true));
        assertThat(Hibernate.isPropertyInitialized(employee12,"addresss"),is(true));
    }

    @Test
    public void showDefaultFetchMode(){
        Employee12 employee12 = session.get(Employee12.class, new Long(2));
        employee12.getAddresss().size();
        transaction.commit();
    }

    @Test
    public void showJoinFetchMode(){
        session.enableFetchProfile("employee_address");
        Employee12 employee12 = session.get(Employee12.class, new Long(2));
        employee12.getAddresss().size();
        transaction.commit();
    }
}