package com.test;

import com.test.simple.Name;
import com.test.simple.Name2;
import com.test.simple.Employee;
import com.test.simple.Employee2;
import com.test.simple.Employee3;
import com.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestHibernate1Simple {
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
//    第一个hibernate程序
    @Test
    public void test1(){
        Employee employee = new Employee();
        employee.setName("jerry");
        employee.setAge(20);

        Long id = (Long)session.save(employee);
        session.flush();
        session.clear();

        Employee employee1 = session.get(Employee.class,id);

        assertThat(employee1,notNullValue());
        assertThat(employee1.getName(),is("jerry"));
    }

//    测试复合主键（EmbeddedId）
    @Test
    public void test2(){
        Employee2 employee2 = new Employee2();
        Name name = new Name();
        name.setFirst("jerry");
        name.setLast("chen");
        employee2.setName(name);
        employee2.setAge(10);
        Name name1 = (Name) session.save(employee2);

        session.getTransaction().commit();
        Employee2 employee21 = session.get(Employee2.class, name1);

        assertThat(employee2, notNullValue());
        assertThat(employee2.getName(), is(name));
        assertThat(employee2.getAge(), is(10));
    }
    //复合主键方式2(在每个id前面加@Id)
    @Test
    public void test3(){
        Employee3 employee3 = new Employee3();
        employee3.setFirst("jerry");
        employee3.setLast("chen");
        employee3.setAge(20);

        //when
        Name2 name2 = (Name2)session.save(employee3);
        session.flush();
        session.clear();
        Employee3 employee31 = session.get(Employee3.class, name2);

        assertThat(employee31, notNullValue());
        assertThat(employee31.getFirst(), is("jerry"));
        assertThat(employee31.getLast(), is("chen"));
    }
}