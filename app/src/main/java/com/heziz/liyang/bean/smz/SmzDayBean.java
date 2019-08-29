package com.heziz.liyang.bean.smz;

import java.io.Serializable;

/**
 * Created by sws on 2019-08-16.
 * from:
 * describe:
 */

public class SmzDayBean implements Serializable {

    private String time;
    private String name;
    private String no;
    private String bz;

    public SmzDayBean(String time, String name, String no, String bz) {
        this.time = time;
        this.name = name;
        this.no = no;
        this.bz = bz;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public String toString() {
        return "SmzDayBean{" +
                "time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", bz='" + bz + '\'' +
                '}';
    }
}
