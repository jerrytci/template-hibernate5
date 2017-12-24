package com.test.inheritance;

import javax.persistence.*;

@Entity
public class PermanentEmployee2 extends Employee7 {
    private int salary;
    private int bonus;

    public PermanentEmployee2() {
    }

    public PermanentEmployee2(int salary, int bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public PermanentEmployee2(Long id, String name, int salary, int bonus) {
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
