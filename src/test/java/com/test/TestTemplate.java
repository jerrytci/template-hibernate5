package com.test;

import com.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TestTemplate {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session = null;

    @BeforeClass
    public static void startup(){
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterClass
    public static void stop(){
        session.close();
        sessionFactory.close();
    }

}
