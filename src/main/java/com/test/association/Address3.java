package com.test.association;

import javax.persistence.*;

@Entity
public class Address3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String steet;
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee12 employee;

    public Address3() {
    }

    public Address3(String steet, String zipcode) {
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

    public Employee12 getEmployee() {
        return employee;
    }

    public void setEmployee(Employee12 employee) {
        this.employee = employee;
    }
}
