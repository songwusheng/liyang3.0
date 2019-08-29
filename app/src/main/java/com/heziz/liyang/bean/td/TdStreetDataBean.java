package com.heziz.liyang.bean.td;

import java.io.Serializable;

/**
 * Created by sws on 2019-08-14.
 * from:
 * describe:
 */

public class TdStreetDataBean implements Serializable {

    /**
     * jdName : 溧城镇
     * jdId : 1542688341537530
     * count : 6
     * online : 0
     * offline : 6
     */

    private String jdName;
    private String jdId;
    private int count;
    private int online;
    private int offline;

    public String getJdName() {
        return jdName;
    }

    public void setJdName(String jdName) {
        this.jdName = jdName;
    }

    public String getJdId() {
        return jdId;
    }

    public void setJdId(String jdId) {
        this.jdId = jdId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }
}
