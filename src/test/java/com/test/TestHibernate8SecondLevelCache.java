package com.test;

import com.test.session.Person2;
import com.test.util.HibernateUtil;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestHibernate8SecondLevelCache {
    private static SessionFactory sessionFactory;
    private static Session session;
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
        if(session.isOpen()){
            session.close();
        }
    }

    @AfterClass
    public static void afterClass(){
        sessionFactory.close();
    }

    @Test
    public void testSecondLevelCache(){
        Person2 person2 = new Person2("person_name");
        session.setCacheMode(CacheMode.NORMAL);
        Statistics statistics = sessionFactory.getStatistics();

        Long id = (Long) session.save(person2);
        transaction.commit();
        session.evict(person2);

        Person2 person21 = session.get(Person2.class,id);
        assertThat(statistics.getSecondLevelCachePutCount(),is(1L));
        session.evict(person21);
        session.get(Person2.class,id);

        assertThat(statistics.getSecondLevelCacheHitCount(),is(1L));
    }
}