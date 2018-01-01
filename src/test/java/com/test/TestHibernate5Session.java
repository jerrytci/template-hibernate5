package com.test;

import com.test.association.*;
import com.test.session.Person;
import com.test.util.HibernateUtil;
import org.hibernate.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class TestHibernate5Session {
    private static SessionFactory sessionFactory;
    private static Session session = null;
    private Transaction transaction;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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

    // should return not null id
    @Test
    public void saveShouldReturnNullId(){
        Person person = getPerson();
        Long id = (Long) session.save(person);
        transaction.commit();
        assertThat(id,notNullValue());
    }

    // should return update object
    @Test
    public void updateShouldReturnUpdateObject(){
        Person person = getPerson();
        Long id = (Long) session.save(person);
        person.setName("haha");
        transaction.commit();
        assertThat(session.get(Person.class,id).getName(),is("haha"));
    }

    @Test
    public void getInvalidObjectShouldReturnNull(){
        assertThat(session.get(Person.class,Long.MAX_VALUE),nullValue());
    }

    @Test
    public void loadInvalidObjectShouldThrowException(){
        expectedException.expect(ObjectNotFoundException.class);
        session.load(Person.class, Long.MAX_VALUE).getName();
    }

    @Test
    public void updateDetachedObjectInCurrentSessionShouldThrowException(){
        expectedException.expect(NonUniqueObjectException.class);
        Session session1 = sessionFactory.openSession();
        Person person = getPerson();
        session1.save(person);
        session1.close();

        Session session2 = sessionFactory.openSession();
        session2.get(Person.class, person.getId());
        session2.update(person);
    }

    @Test
    public void delteThenGetShouldReturnNullObject(){
        Person person = getPerson();
        Long id = (Long) session.save(person);
        assertThat(id, notNullValue());
        session.delete(person);
        transaction.commit();

        assertThat(session.get(Person.class,id),nullValue());
    }

    public  static Person getPerson(){
        return new Person("jerry");
    }
}