package com.yq.pojo;

import java.util.List;

public class Clazz {
    private Integer cid;
    private String cname;
    private List<Student> stus;

    public List<Student> getStus() {
        return stus;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", stud=" + stus +
                '}';
    }

    public void setStus(List<Student> stus) {
        this.stus = stus;
    }

    public Clazz(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public Clazz() {
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
