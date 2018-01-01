package com.test;

import com.test.association.Employee12;
import com.test.session.Student;
import com.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestHibernate7Hql {
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
        session.save(new Student("1"));
        session.save(new Student("2"));
        session.save(new Student("3"));
    }

    @After
    public void after(){
        session.createQuery("delete Student").executeUpdate();
        transaction.commit();
        session.close();
    }

    @AfterClass
    public static void afterClass(){
        sessionFactory.close();
    }

    @Test
    public void testFromClause(){
        List<Student> students = session.createQuery("from Student").list();
        assertThat(students.size(),is(3));
    }

    @Test
    public void testSelectClause(){
        List<Student> students = session.createQuery("select s from Student s").list();
        assertThat(students.size(),is(3));
    }

    @Test
    public void testPolymorphicQueries(){
        List<Employee12> employee12s = session.createQuery("from Employee12").list();
        assertThat(employee12s.size(),is(2));
    }

    @Test
    public void testExpressions(){
        List<Student> students = session.createQuery("select s from Student s where s.name = ?")
                .setString(0,"1").list();
        assertThat(students.get(0).getName(),is("1"));

        List<Student> students2 = session.createQuery("select s from Student s where s.name = :studentName")
                .setString("studentName","1").list();
        assertThat(students2.get(0).getName(),is("1"));
    }
}
