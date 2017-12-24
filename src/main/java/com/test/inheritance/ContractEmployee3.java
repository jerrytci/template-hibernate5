package com.test.inheritance;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ContractEmployee3 extends Employee8 {
    private int daily;
    private int period;

    public ContractEmployee3() {
    }

    public ContractEmployee3(int daily, int period) {
        this.daily = daily;
        this.period = period;
    }

    public ContractEmployee3(Long id, String name, int daily, int period) {
        super(id, name);
        this.daily = daily;
        this.period = period;
    }

    public int getDaily() {
        return daily;
    }

    public void setDaily(int daily) {
        this.daily = daily;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
