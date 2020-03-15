package com.heziz.liyang.bean;

import java.io.Serializable;

/**
 * Created by sws on 2019-12-19.
 * from:
 * describe:
 */

public class JPushCommBean implements Serializable {

    /**
     * badge : 0
     * zcdfc : 0
     * zg : 0
     * zgdsh : 0
     * zxdfc : 0
     * zxdzg : 0
     */

    private String badge;
    private String zcdfc;
    private String zg;
    private String zgdsh;
    private String zxdfc;
    private String zxdzg;

    public JPushCommBean(String badge, String zcdfc, String zg, String zgdsh, String zxdfc, String zxdzg) {
        this.badge = badge;
        this.zcdfc = zcdfc;
        this.zg = zg;
        this.zgdsh = zgdsh;
        this.zxdfc = zxdfc;
        this.zxdzg = zxdzg;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getZcdfc() {
        return zcdfc;
    }

    public void setZcdfc(String zcdfc) {
        this.zcdfc = zcdfc;
    }

    public String getZg() {
        return zg;
    }

    public void setZg(String zg) {
        this.zg = zg;
    }

    public String getZgdsh() {
        return zgdsh;
    }

    public void setZgdsh(String zgdsh) {
        this.zgdsh = zgdsh;
    }

    public String getZxdfc() {
        return zxdfc;
    }

    public void setZxdfc(String zxdfc) {
        this.zxdfc = zxdfc;
    }

    public String getZxdzg() {
        return zxdzg;
    }

    public void setZxdzg(String zxdzg) {
        this.zxdzg = zxdzg;
    }
}
