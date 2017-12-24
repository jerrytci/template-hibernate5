package com.test.association;

import javax.persistence.*;

@Entity
public class Address2 {
    @Id
    private Long id;
    private String steet;
    private String zipcode;

    @OneToOne
    @MapsId
    private Employee11 employee11;

    public Address2() {
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

    public Employee11 getEmployee11() {
        return employee11;
    }

    public void setEmployee11(Employee11 employee11) {
        this.employee11 = employee11;
    }
}
