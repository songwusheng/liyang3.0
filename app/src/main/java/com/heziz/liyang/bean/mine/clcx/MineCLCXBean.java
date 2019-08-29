package com.heziz.liyang.bean.mine.clcx;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-29.
 * from:
 * describe:
 */

public class MineCLCXBean implements Serializable {

    /**
     * projectArea : 中关村
     * projectLeader : null
     * alarmNum : 1
     * pType : null
     * diff : 2
     * projectName : 碧桂园（明昱湾）
     * projectPhone : null
     * projectId : 1555290837479592
     */

    private String projectArea;
    private String projectLeader;
    private int alarmNum;
    private Object pType;
    private String diff;
    private String projectName;
    private String projectPhone;
    private String projectId;

    public String getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public int getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(int alarmNum) {
        this.alarmNum = alarmNum;
    }

    public Object getPType() {
        return pType;
    }

    public void setPType(Object pType) {
        this.pType = pType;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPhone() {
        return projectPhone;
    }

    public void setProjectPhone(String projectPhone) {
        this.projectPhone = projectPhone;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
