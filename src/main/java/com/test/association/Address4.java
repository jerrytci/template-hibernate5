package com.test.association;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address4 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String steet;
    private String zipcode;

    @ManyToMany(mappedBy = "addresss")
    private List<Employee13> employee13s = new ArrayList<Employee13>();

    public Address4() {
    }

    public Address4(String steet, String zipcode) {
        this.steet = steet;
        this.zipcode = zipcode;
    }

    public String getSteet() {
        return steet;
    }

    public void setSteet(String steet) {
        this.steet = steet;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Employee13> getEmployee13s() {
        return employee13s;
    }

    public void setEmployee13s(List<Employee13> employee13s) {
        this.employee13s = employee13s;
    }
}
