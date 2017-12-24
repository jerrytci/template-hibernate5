package com.test.association;

import javax.persistence.*;

@Entity
public class Employee11 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "employee11", cascade = CascadeType.ALL)
    private Address2 address2;

    public Employee11() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address2 getAddress2() {
        return address2;
    }

    public void setAddress2(Address2 address2) {
        this.address2 = address2;
    }
}
