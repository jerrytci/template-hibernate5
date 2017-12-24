package com.test.simple;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Name implements Serializable{
    private String first;
    private String last;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (!first.equals(name.first)) return false;
        return last.equals(name.last);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + last.hashCode();
        return result;
    }
}
