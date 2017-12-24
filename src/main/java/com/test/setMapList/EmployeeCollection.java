package com.test.setMapList;

import com.test.util.PhoneCimparator;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.*;

@Entity
public class EmployeeCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // set mapping: basic type
    @ElementCollection
    @CollectionTable(name = "phones_set_basic", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "employee_phone")
    private Set<String> phones = new HashSet<String>();

    // set mapping: Embeddable type
    @ElementCollection
    @CollectionTable(name = "phones_set_embedded", joinColumns = @JoinColumn(name = "employee_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "phoneType", column = @Column(name = "phone_type")),
            @AttributeOverride(name = "phoneNum", column = @Column(name = "phone_num"))
    })
    private Set<Phone> phonesEmbedded = new HashSet<Phone>();



    // list mapping: basic type and indexed list
    @ElementCollection
    @CollectionTable(name = "phones_list_basic_indexed", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "phone_num")
    @OrderColumn(name = "phone_order")
    private List<String> phoneList = new ArrayList<String>();

    // list mapping: basic type and order list
    @ElementCollection
    @CollectionTable(name = "phones_list_basic_ordered", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "phone_num")
    @OrderBy
    private List<String> phoneListOrder = new ArrayList<String>();

    // list mapping: embededable type and indexed list
    @ElementCollection
    @CollectionTable(name = "phones_list_embededable_indexed", joinColumns = @JoinColumn(name = "employee_id"))
    @OrderColumn(name = "phone_order")
    @AttributeOverrides({
            @AttributeOverride(name = "phoneType", column = @Column(name = "phone_type")),
            @AttributeOverride(name = "phoneNum", column = @Column(name = "phone_num"))
    })
    private List<Phone> phoneListEmbededable = new ArrayList<Phone>();



    // map mapping: key is basic and value is basic
    @ElementCollection
    @CollectionTable(name = "phones_map_basic", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "map_value")
    @MapKeyColumn(name = "map_key")
    private Map<String, String> map = new HashMap<String, String>();

    // map mapping: key is basic and value is embeddable
    @ElementCollection
    @CollectionTable(name = "phones_map_value_is_embeddable", joinColumns = @JoinColumn(name = "employee_id"))
    @MapKeyColumn(name = "phone_key")
    private Map<String, Phone> phoneMap = new HashMap<String, Phone>();

    @ElementCollection
    @CollectionTable(name = "phones_map_key_is_embeddable", joinColumns = @JoinColumn(name = "employee_id"))
    private Map<Project, Float> gradle = new HashMap<Project, Float>();



    // sortedSet mapping
    @ElementCollection
    @CollectionTable(name = "phones_set_sorted", joinColumns = @JoinColumn(name = "employee_id"))
    @SortComparator(PhoneCimparator.class)
    private SortedSet<Phone> phoneSortedSet = new TreeSet<Phone>();

    public EmployeeCollection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public Set<Phone> getPhonesEmbedded() {
        return phonesEmbedded;
    }

    public void setPhonesEmbedded(Set<Phone> phonesEmbedded) {
        this.phonesEmbedded = phonesEmbedded;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public List<String> getPhoneListOrder() {
        return phoneListOrder;
    }

    public void setPhoneListOrder(List<String> phoneListOrder) {
        this.phoneListOrder = phoneListOrder;
    }

    public List<Phone> getPhoneListEmbededable() {
        return phoneListEmbededable;
    }

    public void setPhoneListEmbededable(List<Phone> phoneListEmbededable) {
        this.phoneListEmbededable = phoneListEmbededable;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, Phone> getPhoneMap() {
        return phoneMap;
    }

    public void setPhoneMap(Map<String, Phone> phoneMap) {
        this.phoneMap = phoneMap;
    }

    public Map<Project, Float> getGradle() {
        return gradle;
    }

    public void setGradle(Map<Project, Float> gradle) {
        this.gradle = gradle;
    }

    public SortedSet<Phone> getPhoneSortedSet() {
        return phoneSortedSet;
    }

    public void setPhoneSortedSet(SortedSet<Phone> phoneSortedSet) {
        this.phoneSortedSet = phoneSortedSet;
    }
}
