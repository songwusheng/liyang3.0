package com.heziz.liyang.bean.zh;

import java.io.Serializable;

/**
 * Created by sws on 2019-08-14.
 * from:
 * describe:
 */

public class TdDataBean implements Serializable{

    /**
     * count : 25
     * online : 1
     * offline : 24
     */

    private int count;
    private int online;
    private int offline;

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
