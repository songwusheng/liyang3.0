package com.heziz.liyang.bean.yc;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by sws on 2019-05-14.
 * from:
 * describe:
 */

public class YczlxNumBean1 implements MultiItemEntity {
    private int onlineCount;
    private String avgValue;
    private int offlineCount;
    private String street;
    private String name;
    private String projectId;
    private String managerRoleIds;
    private int totalCount;
    private List<YconlineofflineDeviceBean> deviceList;

    public List<YconlineofflineDeviceBean> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<YconlineofflineDeviceBean> deviceList) {
        this.deviceList = deviceList;
    }

    public String getManagerRoleIds() {
        return managerRoleIds;
    }

    public void setManagerRoleIds(String managerRoleIds) {
        this.managerRoleIds = managerRoleIds;
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

    @Override
    public int getItemType() {
        return 0;
    }
}
