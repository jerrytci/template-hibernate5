package com.test.util;

import com.test.inheritance.*;
import com.test.session.Person;
import com.test.session.Person2;
import com.test.session.Student;
import com.test.session.SubEmployee12;
import com.test.setMapList.EmployeeCollection;
import com.test.association.*;
import com.test.simple.Employee;
import com.test.simple.Employee2;
import com.test.simple.Employee3;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(serviceRegistry)
//                    po及简单属性映射
                    .addAnnotatedClass(Employee.class)
//                    复合主键映射
                    .addAnnotatedClass(Employee2.class)
//                    复合主键映射方式2
                    .addAnnotatedClass(Employee3.class)

                    // collection
                    .addAnnotatedClass(EmployeeCollection.class)

                    // inheritance
                    .addAnnotatedClass(Employee6.class)
                    .addAnnotatedClass(PermanentEmployee.class)
                    .addAnnotatedClass(ContractEmployee.class)
                    // 2
                    .addAnnotatedClass(Employee7.class)
                    .addAnnotatedClass(PermanentEmployee2.class)
                    .addAnnotatedClass(ContractEmployee2.class)
                    // 3 共同的属性保存在父类，子类的属性保存在子类中
                    .addAnnotatedClass(Employee8.class)
                    .addAnnotatedClass(PermanentEmployee3.class)
                    .addAnnotatedClass(ContractEmployee3.class)

                    // Relational Mapping
                    // one to one
                    .addAnnotatedClass(Employee10.class)
                    .addAnnotatedClass(Address.class)
                    // one to one (2)
                    .addAnnotatedClass(Employee11.class)
                    .addAnnotatedClass(Address2.class)
                    // one to many
                    .addAnnotatedClass(Employee12.class)
                    .addAnnotatedClass(Address3.class)
                    // many to many
                    .addAnnotatedClass(Employee13.class)
                    .addAnnotatedClass(Address4.class)
                    // session
                    .addAnnotatedClass(Person.class)
                    // hql
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(SubEmployee12.class)

                    .addAnnotatedClass(Person2.class)
                    .buildMetadata();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }
}
