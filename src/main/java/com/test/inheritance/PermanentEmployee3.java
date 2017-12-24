package com.test.inheritance;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PermanentEmployee3 extends Employee8 {
    private int salary;
    private int bonus;

    public PermanentEmployee3() {
    }

    public PermanentEmployee3(int salary, int bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public PermanentEmployee3(Long id, String name, int salary, int bonus) {
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
