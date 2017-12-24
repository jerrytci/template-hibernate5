package com.test.setMapList;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Phone {
    private  String phoneType;
    private String phoneNum;

    public Phone() {
    }

    public Phone(String phoneType, String phoneNum) {
        this.phoneType = phoneType;
        this.phoneNum = phoneNum;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
