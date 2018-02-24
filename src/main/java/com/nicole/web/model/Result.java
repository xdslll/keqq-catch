package com.nicole.web.model;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public class Result {

    private int total;
    private List<Orgnization> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Orgnization> getRows() {
        return rows;
    }

    public void setRows(List<Orgnization> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Result{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
