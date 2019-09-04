package com.heziz.liyang.bean.yc;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class YcProjectDBean<T> implements Serializable {

    /**
     * id : 1556503888314802
     * createBy : 1
     * createTime : 2019-04-29 10:11:28
     * updateBy : null
     * updateTime : 2019-04-29 10:11:28
     * weacherDeviceId : HC602019_YCZSJC_040015
     * projectId : null
     * area : null
     * projectname : null
     * managerrolename : null
     * projectArea : null
     * projectLeader : null
     * stationname : null
     * everytime : null
     * weatherDeviceName : 气象站
     * weatherDeviceIsonline : null
     * weatherDeviceOnlinetime : null
     * weatherDeviceLongitude :
     * avgvalue : 0
     * weatherDeviceLatitude :
     * weatherDeviceEndpoint : 1551258680345413
     * delFlag : 0
     * mWeatherProjectDevice :
     */

    private long id;
    private long createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private String weacherDeviceId;
    private Object projectId;
    private Object area;
    private Object projectname;
    private Object managerrolename;
    private Object projectArea;
    private Object projectLeader;
    private Object stationname;
    private Object everytime;
    private String weatherDeviceName;
    private Object weatherDeviceIsonline;
    private Object weatherDeviceOnlinetime;
    private String weatherDeviceLongitude;
    private int avgvalue;
    private String weatherDeviceLatitude;
    private String weatherDeviceEndpoint;
    private String delFlag;
    private T mWeatherProjectDevice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWeacherDeviceId() {
        return weacherDeviceId;
    }

    public void setWeacherDeviceId(String weacherDeviceId) {
        this.weacherDeviceId = weacherDeviceId;
    }

    public Object getProjectId() {
        return projectId;
    }

    public void setProjectId(Object projectId) {
        this.projectId = projectId;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public Object getProjectname() {
        return projectname;
    }

    public void setProjectname(Object projectname) {
        this.projectname = projectname;
    }

    public Object getManagerrolename() {
        return managerrolename;
    }

    public void setManagerrolename(Object managerrolename) {
        this.managerrolename = managerrolename;
    }

    public Object getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(Object projectArea) {
        this.projectArea = projectArea;
    }

    public Object getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(Object projectLeader) {
        this.projectLeader = projectLeader;
    }

    public Object getStationname() {
        return stationname;
    }

    public void setStationname(Object stationname) {
        this.stationname = stationname;
    }

    public Object getEverytime() {
        return everytime;
    }

    public void setEverytime(Object everytime) {
        this.everytime = everytime;
    }

    public String getWeatherDeviceName() {
        return weatherDeviceName;
    }

    public void setWeatherDeviceName(String weatherDeviceName) {
        this.weatherDeviceName = weatherDeviceName;
    }

    public Object getWeatherDeviceIsonline() {
        return weatherDeviceIsonline;
    }

    public void setWeatherDeviceIsonline(Object weatherDeviceIsonline) {
        this.weatherDeviceIsonline = weatherDeviceIsonline;
    }

    public Object getWeatherDeviceOnlinetime() {
        return weatherDeviceOnlinetime;
    }

    public void setWeatherDeviceOnlinetime(Object weatherDeviceOnlinetime) {
        this.weatherDeviceOnlinetime = weatherDeviceOnlinetime;
    }

    public String getWeatherDeviceLongitude() {
        return weatherDeviceLongitude;
    }

    public void setWeatherDeviceLongitude(String weatherDeviceLongitude) {
        this.weatherDeviceLongitude = weatherDeviceLongitude;
    }

    public int getAvgvalue() {
        return avgvalue;
    }

    public void setAvgvalue(int avgvalue) {
        this.avgvalue = avgvalue;
    }

    public String getWeatherDeviceLatitude() {
        return weatherDeviceLatitude;
    }

    public void setWeatherDeviceLatitude(String weatherDeviceLatitude) {
        this.weatherDeviceLatitude = weatherDeviceLatitude;
    }

    public String getWeatherDeviceEndpoint() {
        return weatherDeviceEndpoint;
    }

    public void setWeatherDeviceEndpoint(String weatherDeviceEndpoint) {
        this.weatherDeviceEndpoint = weatherDeviceEndpoint;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public T getMWeatherProjectDevice() {
        return mWeatherProjectDevice;
    }

    public void setMWeatherProjectDevice(T mWeatherProjectDevice) {
        this.mWeatherProjectDevice = mWeatherProjectDevice;
    }
}
