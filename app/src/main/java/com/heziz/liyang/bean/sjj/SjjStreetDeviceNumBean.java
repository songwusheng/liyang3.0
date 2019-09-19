package com.heziz.liyang.bean.sjj;

import java.io.Serializable;

/**
 * Created by sws on 2019-09-17.
 * from:
 * describe:
 */

public class SjjStreetDeviceNumBean implements Serializable {

    /**
     * id : 1542688107406036
     * name : 燕山新区
     * total : 3
     * offline : 0
     * online : 3
     */

    private long id;
    private String name;
    private int total;
    private int offline;
    private int online;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}
