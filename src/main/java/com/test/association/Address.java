package com.test.association;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String steet;
    private String zipcode;

    @OneToOne
    @JoinColumn(name = "employee10_id")
    private Employee10 employee10;

    public Address() {
    }

    public Address(String steet, String zipcode, Employee10 employee10) {
        this.steet = steet;
        this.zipcode = zipcode;
        this.employee10 = employee10;
    }

    public Employee10 getEmployee10() {
        return employee10;
    }

    public void setEmployee10(Employee10 employee10) {
        this.employee10 = employee10;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
