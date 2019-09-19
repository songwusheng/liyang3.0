package com.heziz.liyang.bean.sjj;

import java.io.Serializable;

/**
 * Created by sws on 2019-09-17.
 * from:
 * describe:
 */

public class SjjDeviceNumBean implements Serializable {

    /**
     * offline : 0
     * online : 3
     * total : 3
     */

    private String offline;
    private String online;
    private String total;

    public String getOffline() {
        return offline;
    }

    public void setOffline(String offline) {
        this.offline = offline;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
