package com.test;

import com.test.util.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println(sessionFactory);
    }
}
