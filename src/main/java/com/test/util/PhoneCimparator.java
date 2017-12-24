package com.test.util;

import com.test.setMapList.Phone;

import java.util.Comparator;

public class PhoneCimparator implements Comparator<Phone> {

    public int compare(Phone o1, Phone o2) {
        return o1.getPhoneNum().compareTo(o2.getPhoneNum());
    }
}
