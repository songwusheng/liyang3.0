package com.heziz.liyang.bean.car;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-31.
 * from:
 * describe:
 */

public class CarZHBean implements Serializable{

    /**
     * davId : 1000903
     * oon : 在线
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
