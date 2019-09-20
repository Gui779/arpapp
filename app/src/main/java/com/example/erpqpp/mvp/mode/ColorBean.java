package com.example.erpqpp.mvp.mode;

import java.io.Serializable;

public class ColorBean implements Serializable {
    private String colorname;
    private String colorid;

    public ColorBean(String colorname, String colorid) {
        this.colorname = colorname;
        this.colorid = colorid;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }
}
