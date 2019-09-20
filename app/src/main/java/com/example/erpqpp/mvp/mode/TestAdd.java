package com.example.erpqpp.mvp.mode;

import java.io.Serializable;

public class TestAdd implements Serializable {
    private String name;
    private String count;

    public TestAdd(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public TestAdd() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
