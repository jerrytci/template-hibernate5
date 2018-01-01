package com.test.session;

import com.test.association.Employee12;

import javax.persistence.Entity;

@Entity
public class SubEmployee12 extends Employee12 {
    private  String number;

    public SubEmployee12() {
    }

    public SubEmployee12(String name,String number) {
        super(name);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
