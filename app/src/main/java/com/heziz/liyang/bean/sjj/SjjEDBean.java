package com.heziz.liyang.bean.sjj;

import java.io.Serializable;

/**
 * Created by sws on 2019-09-17.
 * from:
 * describe:
 */

public class SjjEDBean implements Serializable {

    /**
     * id : 136
     * createBy : 0
     * createTime : 2019-09-10 11:23:09
     * updateBy : 0
     * updateTime : 2019-09-10 11:55:53
     * projectId : 1540783343868791
     * devNum : 9000006
     * devName : 银江测试
     * building : 2
     * projName : null
     * siteName : null
     * site : null
     * pids : null
     * isOn : 1
     * mName : null
     * limitHeight : 60.0
     * limitWeight : 2.0
     * limitBatter : 3.0
     */

    private int id;
    private int createBy;
    private String createTime;
    private int updateBy;
    private String updateTime;
    private long projectId;
    private String devNum;
    private String devName;
    private String building;
    private Object projName;
    private Object siteName;
    private Object site;
    private Object pids;
    private int isOn;
    private Object mName;
    private double limitHeight;
    private double limitWeight;
    private double limitBatter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Object getProjName() {
        return projName;
    }

    public void setProjName(Object projName) {
        this.projName = projName;
    }

    public Object getSiteName() {
        return siteName;
    }

    public void setSiteName(Object siteName) {
        this.siteName = siteName;
    }

    public Object getSite() {
        return site;
    }

    public void setSite(Object site) {
        this.site = site;
    }

    public Object getPids() {
        return pids;
    }

    public void setPids(Object pids) {
        this.pids = pids;
    }

    public int getIsOn() {
        return isOn;
    }

    public void setIsOn(int isOn) {
        this.isOn = isOn;
    }

    public Object getMName() {
        return mName;
    }

    public void setMName(Object mName) {
        this.mName = mName;
    }

    public double getLimitHeight() {
        return limitHeight;
    }

    public void setLimitHeight(double limitHeight) {
        this.limitHeight = limitHeight;
    }

    public double getLimitWeight() {
        return limitWeight;
    }

    public void setLimitWeight(double limitWeight) {
        this.limitWeight = limitWeight;
    }

    public double getLimitBatter() {
        return limitBatter;
    }

    public void setLimitBatter(double limitBatter) {
        this.limitBatter = limitBatter;
    }
}
