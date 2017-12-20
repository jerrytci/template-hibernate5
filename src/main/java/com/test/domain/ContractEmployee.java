package com.test.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("contract")
public class ContractEmployee extends Employee6 {
    private int daily;
    private int period;

    public ContractEmployee() {
    }

    public ContractEmployee(int daily, int period) {
        this.daily = daily;
        this.period = period;
    }

    public ContractEmployee(Long id, String name, int daily, int period) {
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
