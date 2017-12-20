package com.test.util;

import com.test.domain.ContractEmployee;
import com.test.domain.Employee;
import com.test.domain.Employee6;
import com.test.domain.PermanentEmployee;
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
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Employee6.class)
                    .addAnnotatedClass(PermanentEmployee.class)
                    .addAnnotatedClass(ContractEmployee.class)
                    .buildMetadata();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }
}
