package com.test.association;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@FetchProfile(name = "employee_address",fetchOverrides = {@FetchProfile.FetchOverride(
        entity = Employee12.class,
        association = "addresss",
        mode = FetchMode.JOIN)
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee12 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Address3> addresss = new ArrayList<Address3>();

    public Employee12() {

    }

    public Employee12(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address3> getAddresss() {
        return addresss;
    }

    public void setAddresss(List<Address3> addresss) {
        this.addresss = addresss;
    }
}
