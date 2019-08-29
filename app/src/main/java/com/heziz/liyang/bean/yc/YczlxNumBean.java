package com.heziz.liyang.bean.yc;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-14.
 * from:
 * describe:
 */

public class YczlxNumBean implements Serializable {

    /**
     * onlineCount : 0
     * avgValue : .00
     * offlineCount : 0
     * street : 栖霞
     * totalCount : 0
     */

    private int onlineCount;
    private String avgValue;
    private int offlineCount;
    private String street;
    private String name;
    private String projectId;
    private String managerRoleId;
    private int totalCount;

    public String getManagerRoleId() {
        return managerRoleId;
    }

    public void setManagerRoleId(String managerRoleId) {
        this.managerRoleId = managerRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(String avgValue) {
        this.avgValue = avgValue;
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
}
