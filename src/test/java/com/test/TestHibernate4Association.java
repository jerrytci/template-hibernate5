package com.test;

import com.test.association.*;
import com.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestHibernate4Association {
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
    public void testOnetoOneOnForeignKey(){
        Employee10 employee10 = new Employee10();
        employee10.setName("employee10_name");
        Address address = new Address();
        address.setSteet("steet_name");
        address.setZipcode("2000");
        employee10.setAddress(address);
        address.setEmployee10(employee10);

        Long id = (Long) session.save(employee10);
        session.clear();

        Employee10 employee101 = session.get(Employee10.class, id);

        assertThat(employee101, notNullValue());
        assertThat(employee101.getAddress(), notNullValue());
    }

    @Test
    public void testOnetoOneOnAPrimaryKey(){
        Employee11 employee11 = new Employee11();
        employee11.setName("employee11_name");
        Address2 address2 = new Address2();
        address2.setSteet("steet_name");
        address2.setZipcode("2000");
        employee11.setAddress2(address2);
        address2.setEmployee11(employee11);

        Long id = (Long) session.save(employee11);
        transaction.commit();
        session.clear();

        Employee11 employee101 = session.get(Employee11.class, id);

        assertThat(employee101, notNullValue());
        assertThat(employee101.getAddress2(), notNullValue());
    }
}