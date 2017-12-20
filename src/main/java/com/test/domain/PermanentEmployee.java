package com.test.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(("employee"))
public class PermanentEmployee extends Employee6{
    private int salary;
    private int bonus;

    public PermanentEmployee() {
    }

    public PermanentEmployee(int salary, int bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public PermanentEmployee(Long id, String name, int salary, int bonus) {
        super(id, name);
        this.salary = salary;
        this.bonus = bonus;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
