package com.heziz.liyang.bean.car;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sws on 2019-05-27.
 * from:
 * describe:
 */

public class CarProjectBean implements Serializable {

    /**
     * onlineCount : 1
     * offlineCount : 0
     * name : 上兴码头项目
     * davstring : []
     * totalCount : 1
     * unknow
     * projectId : 1555401956391821
     */

    private int onlineCount;
    private int offlineCount;
    private String name;
    private int totalCount;
    private int unknow;
    private String projectId;
    private List<CarZHBean> davstring;

    public int getUnknow() {
        return unknow;
    }

    public void setUnknow(int unknow) {
        this.unknow = unknow;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<CarZHBean> getDavstring() {
        return davstring;
    }

    public void setDavstring(List<CarZHBean> davstring) {
        this.davstring = davstring;
    }
}
