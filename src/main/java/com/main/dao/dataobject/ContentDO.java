package com.main.dao.dataobject;

import java.util.Date;

public class ContentDO {
    private Integer id;

    private Integer a;

    private String b;

    private String c;

    private Date gmtCreate;

    private String d;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}