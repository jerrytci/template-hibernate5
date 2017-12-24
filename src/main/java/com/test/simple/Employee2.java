package com.test.simple;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

//复合主键映射
@Entity
public class Employee2 {
    @EmbeddedId
    private Name name;
    private int age;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
