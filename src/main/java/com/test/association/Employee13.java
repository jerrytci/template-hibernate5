package com.test.association;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee13 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "employee_address",joinColumns = @JoinColumn(name = "employee_id",referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "address_id",referencedColumnName = "id"))
    private List<Address4> addresss = new ArrayList<Address4>();

    public Employee13() {
    }

    public Employee13(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address4> getAddresss() {
        return addresss;
    }

    public void setAddresss(List<Address4> addresss) {
        this.addresss = addresss;
    }
}
