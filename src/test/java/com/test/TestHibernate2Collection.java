package com.test;

import com.test.setMapList.EmployeeCollection;
import com.test.setMapList.Phone;
import com.test.setMapList.Project;
import com.test.util.HibernateUtil;
import com.test.util.PhoneCimparator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestHibernate2Collection {
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

//    测试set<basic>
    @Test
    public void testSet(){
        //Given
        EmployeeCollection employeeCollection =new EmployeeCollection();
        employeeCollection.setName("jerry");;
        Set<String> phones = new HashSet<String>();
        phones.add("123456");
        phones.add("654123");
        employeeCollection.setPhones(phones);

        //whnen
        Long id = (Long)session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);

        assertThat(employeeCollection1, notNullValue());
        assertThat(employeeCollection1.getName(), is("jerry"));
        assertThat(employeeCollection1.getPhones(), is(phones));
    }

    //    测试set<Embedded>
    @Test
    public void testSetEmbedde(){
        EmployeeCollection employeeCollection = new EmployeeCollection();
        employeeCollection.setName("jerry");
        Set<Phone> phones = new HashSet<Phone>();
        phones.add(new Phone("home", "123456"));
        phones.add(new Phone("mobile", "654321"));
        employeeCollection.setPhonesEmbedded(phones);

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);
    }

    //    测试List<basic> and indexed list
    @Test
    public void testListBasicAndIndexed(){
        EmployeeCollection employeeCollection = new EmployeeCollection();
        employeeCollection.setName("list_basic_indexed");
        List<String> phoneList = new ArrayList<String>();
        phoneList.add("123456");
        phoneList.add("654321");
        phoneList.add("1254623");
        employeeCollection.setPhoneList(phoneList);

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);

        assertThat(employeeCollection1, notNullValue());
    }

    //    测试List<basic> and Ordered list
    @Test
    public void testListBasicAndOrdered(){
        EmployeeCollection employeeCollection = new EmployeeCollection();
        employeeCollection.setName("list_basic_order");
        List<String> phoneList2 = new ArrayList<String>();
        phoneList2.add("123456");
        phoneList2.add("543210");
        phoneList2.add("345678");
        employeeCollection.setPhoneListOrder(phoneList2);

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);

        assertThat(employeeCollection1, notNullValue());
        assertThat(employeeCollection1.getPhoneListOrder().get(0), is("123456"));
        assertThat(employeeCollection1.getPhoneListOrder().get(1), is("345678"));
        assertThat(employeeCollection1.getPhoneListOrder().get(2), is("543210"));
    }

    //    测试List<embeddable> and indexed list
    @Test
    public void testListEmbeddableIndex(){
        EmployeeCollection employeeCollection = new EmployeeCollection();
        employeeCollection.setName("list_embeddable_index");
        List<Phone> phoneList = new ArrayList<Phone>();
        phoneList.add(new Phone("home", "123456"));
        phoneList.add(new Phone("mobile", "654321"));
        employeeCollection.setPhoneListEmbededable(phoneList);

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);

        assertThat(employeeCollection1, notNullValue());
    }



    // map mapping: key is basic and value is basic
    @Test
    public void testMapBasic(){
        EmployeeCollection employeeCollection = new EmployeeCollection();
        Map<String,String> map = new HashMap<String, String>();
        map.put("home","123456");
        map.put("mobile","654321");
        employeeCollection.setMap(map);
        employeeCollection.setName("map_basic");

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);

        assertThat(employeeCollection1, notNullValue());
    }

    // map mapping: key is basic and value is embeddable
    @Test
    public void test_phones_map_value_is_embeddable(){
        EmployeeCollection employeeCollection = new EmployeeCollection();
        Map<String,Phone> map = new HashMap<String, Phone>();
        map.put("1", new Phone("home","123456"));
        map.put("2", new Phone("mobile","654321"));
        employeeCollection.setPhoneMap(map);
        employeeCollection.setName("testMap_KeyisEmbeddable");

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);

        assertThat(employeeCollection1, notNullValue());
    }

    // map mapping: key is basic and value is embeddable
    @Test
    public void test_phones_map_key_is_embeddable(){
        EmployeeCollection employeeCollection = new EmployeeCollection();
        Map<Project,Float> gradle = new HashMap<Project, Float>();
        gradle.put(new Project("home"), Float.valueOf(10));
        gradle.put(new Project("mobile"), Float.valueOf(20));
        employeeCollection.setGradle(gradle);
        employeeCollection.setName("test_phones_map_key_is_embeddable");

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);

        assertThat(employeeCollection1, notNullValue());
    }

    // sortedSet mapping
    @Test
    public void test_sortedSet(){
        /*EmployeeCollection employeeCollection = new EmployeeCollection();
        SortedSet<Phone> phoneSortedMap = new TreeSet<Phone>(new PhoneCimparator());
        phoneSortedMap.add(new Phone("heyuan", "2"));
        phoneSortedMap.add(new Phone("heping", "4"));
        phoneSortedMap.add(new Phone("yousheng", "1"));
        phoneSortedMap.add(new Phone("shenzhen", "5"));
        phoneSortedMap.add(new Phone("guangzhou", "3"));
        employeeCollection.setPhoneSortedSet(phoneSortedMap);
        employeeCollection.setName("test_sortedSet");

        Long id = (Long) session.save(employeeCollection);
        session.flush();
        session.clear();
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, id);*/
        EmployeeCollection employeeCollection1 = session.get(EmployeeCollection.class, Long.valueOf(4));

        assertThat(employeeCollection1, notNullValue());
        Iterator<Phone> iterator = employeeCollection1.getPhoneSortedSet().iterator();
        assertThat(iterator.next().getPhoneNum(), is("1"));
        assertThat(iterator.next().getPhoneNum(), is("2"));
        assertThat(iterator.next().getPhoneNum(), is("3"));
        assertThat(iterator.next().getPhoneNum(), is("4"));
        assertThat(iterator.next().getPhoneNum(), is("5"));
    }















}