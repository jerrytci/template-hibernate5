package com.test;

import com.test.association.*;
import com.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testOneToMay(){
        Address3 address3 = new Address3("s1","2001");
        Address3 address3_2 = new Address3("s2","2002");
        Address3 address3_3 = new Address3("s3","2003");
        List<Address3> address3s = new ArrayList<Address3>();
        address3s.add(address3);
        address3s.add(address3_2);
        address3s.add(address3_3);

        Employee12 employee12 = new Employee12();
        employee12.setName("employee");
        employee12.setAddresss(address3s);

        address3.setEmployee(employee12);
        address3_2.setEmployee(employee12);
        address3_3.setEmployee(employee12);

        Long id = (Long) session.save(employee12);
        transaction.commit();
        session.clear();
        Employee12 employee121 = session.get(Employee12.class, id);

        assertThat(employee121, notNullValue());
        assertThat(employee121.getAddresss(), notNullValue());
    }

    @Test
    public void testManyToMany(){
        Employee13 employee13 = new Employee13("name1");
        Employee13 employee131 = new Employee13("name2");

        Address4 address4 = new Address4("s1","2001");
        Address4 address4_2 = new Address4("s2","2002");

        employee13.getAddresss().add(address4);
        employee13.getAddresss().add(address4_2);
        employee131.getAddresss().add(address4);
        employee131.getAddresss().add(address4_2);

        session.save(employee13);
        session.save(employee131);
        session.save(address4);
        session.save(address4_2);
        transaction.commit();
    }

}