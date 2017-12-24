package com.test.simple;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Name2.class)
public class Employee3 {
    @Id
    private  String first;
    @Id
    private String last;

    private  int age;

    public Employee3() {
    }

    public Employee3(String first, String last, int age) {
        this.first = first;
        this.last = last;
        this.age = age;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
