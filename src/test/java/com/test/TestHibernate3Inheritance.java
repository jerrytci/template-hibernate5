package com.test;

import com.test.inheritance.*;
import com.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestHibernate3Inheritance {
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

    @Test
    public void testInheritanceMappingPerHierarchy(){
        Employee6 employee6 = new Employee6();
        employee6.setName("employee_name_1");

        PermanentEmployee permanentEmployee = new PermanentEmployee();
        permanentEmployee.setName("permanent_employee_name_1");
        permanentEmployee.setSalary(10000);
        permanentEmployee.setBonus(5000);

        ContractEmployee contractEmployee = new ContractEmployee();
        contractEmployee.setName("contract_employee_name_1");
        contractEmployee.setDaily(500);
        contractEmployee.setPeriod(6);

        Long id =  (Long)session.save(employee6);
        Long id2 =  (Long)session.save(permanentEmployee);
        Long id3 =  (Long)session.save(contractEmployee);
        session.flush();
        session.clear();

        Employee6 employee61 = session.get(Employee6.class, id);
        PermanentEmployee permanentEmployee1 = session.get(PermanentEmployee.class, id2);
        ContractEmployee contractEmployee1 = session.get(ContractEmployee.class, id3);

        assertThat(employee61, notNullValue());
        assertThat(permanentEmployee1, notNullValue());
        assertThat(contractEmployee1, notNullValue());
    }

    // per class use per table
    @Test
    public void testInheritance2(){
        Employee7 employee7 = new Employee7();
        employee7.setName("testInheritance");

        PermanentEmployee2 permanentEmployee2 = new PermanentEmployee2();
        permanentEmployee2.setName("PermanentEmployee2");
        permanentEmployee2.setSalary(10000);
        permanentEmployee2.setBonus(5000);

        ContractEmployee2 contractEmployee2 = new ContractEmployee2();
        contractEmployee2.setName("ContractEmployee2");
        contractEmployee2.setDaily(500);
        contractEmployee2.setPeriod(6);

        Long id1 = (Long) session.save(employee7);
        Long id2 = (Long) session.save(permanentEmployee2);
        Long id3 = (Long) session.save(contractEmployee2);
        session.flush();
        session.clear();

        Employee7 employee71 = session.get(Employee7.class, id1);
        PermanentEmployee2 permanentEmployee21 = session.get(PermanentEmployee2.class, id2);
        ContractEmployee2 contractEmployee21 = session.get(ContractEmployee2.class, id3);

        assertThat(employee71, notNullValue());
        assertThat(permanentEmployee21, notNullValue());
        assertThat(contractEmployee21, notNullValue());
    }

    // 共同的属性保存在父类，子类的属性保存在子类中
    @Test
    public void testInheritance3(){
        Employee8 employee8 = new Employee8();
        employee8.setName("testInheritance");

        PermanentEmployee3 permanentEmployee3 = new PermanentEmployee3();
        permanentEmployee3.setName("PermanentEmployee3");
        permanentEmployee3.setSalary(10000);
        permanentEmployee3.setBonus(5000);

        ContractEmployee3 contractEmployee3 = new ContractEmployee3();
        contractEmployee3.setName("contractEmployee3");
        contractEmployee3.setDaily(500);
        contractEmployee3.setPeriod(6);

        Long id1 = (Long) session.save(employee8);
        Long id2 = (Long) session.save(permanentEmployee3);
        Long id3 = (Long) session.save(contractEmployee3);
        session.flush();
        session.clear();

        Employee8 employee81 = session.get(Employee8.class, id1);
        PermanentEmployee3 permanentEmployee31 = session.get(PermanentEmployee3.class, id2);
        ContractEmployee3 contractEmployee31 = session.get(ContractEmployee3.class, id3);

        assertThat(employee81, notNullValue());
        assertThat(permanentEmployee31, notNullValue());
        assertThat(contractEmployee31, notNullValue());
    }
}