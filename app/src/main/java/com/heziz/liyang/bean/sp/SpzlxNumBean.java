package com.heziz.liyang.bean.sp;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-22.
 * from:
 * describe:
 */

public class SpzlxNumBean implements Serializable {

    /**
     * onlineCount : 12
     * offlineCount : 12
     * street : 迈皋桥
     * totalCount : 24
     * unknowCount
     * streetID : 1552294546757158
     */

    private int onlineCount;
    private int offlineCount;
    private String street;
    private int totalCount;
    private int unknowCount;
    private String streetID;

    public int getUnknowCount() {
        return unknowCount;
    }

    public void setUnknowCount(int unknowCount) {
        this.unknowCount = unknowCount;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public int getOfflineCount() {
        return offlineCount;
    }

    public void setOfflineCount(int offlineCount) {
        this.offlineCount = offlineCount;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getStreetID() {
        return streetID;
    }

    public void setStreetID(String streetID) {
        this.streetID = streetID;
    }
}
