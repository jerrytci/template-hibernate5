package com.test.inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class ContractEmployee2 extends Employee7 {
    private int daily;
    private int period;

    public ContractEmployee2() {
    }

    public ContractEmployee2(int daily, int period) {
        this.daily = daily;
        this.period = period;
    }

    public ContractEmployee2(Long id, String name, int daily, int period) {
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
