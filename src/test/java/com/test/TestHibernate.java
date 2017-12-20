package com.test;

import com.test.domain.ContractEmployee;
import com.test.domain.Employee;
import com.test.domain.Employee6;
import com.test.domain.PermanentEmployee;
import com.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestHibernate {
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
    public void test(){

    }

    @Test
    public void testAddEmployee(){
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
    }


}