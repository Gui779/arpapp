package com.example.erpqpp.mvp.mode;

import java.io.Serializable;

public class Mgbean implements Serializable {
  private String id;
  private String store_id;
  private String count;

    public Mgbean(String id, String store_id, String count) {
        this.id = id;
        this.store_id = store_id;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
