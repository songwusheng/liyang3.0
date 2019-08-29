package com.heziz.liyang.bean.yc;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-16.
 * from:
 * describe:
 */

public class YconlineofflineDeviceBean implements Serializable {

    /**
     * davId : HC602019_YCZSJC_040021
     * oon : 离线
     */

    private String davId;
    private String oon;

    public String getDavId() {
        return davId;
    }

    public void setDavId(String davId) {
        this.davId = davId;
    }

    public String getOon() {
        return oon;
    }

    public void setOon(String oon) {
        this.oon = oon;
    }
}
