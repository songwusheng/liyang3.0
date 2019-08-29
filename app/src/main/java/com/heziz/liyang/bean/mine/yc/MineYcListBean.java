package com.heziz.liyang.bean.mine.yc;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-29.
 * from:
 * describe:
 */

public class MineYcListBean implements Serializable {

    /**
     * alarmTotalCount : 7
     * projectArea : 尧化街道
     * projectLeader : 张勇
     * pType : null
     * diff : 2
     * alarmDeviceCount : 1
     * projectName : 尧化新村站-南京地铁七号线D7-TA03标六区项目
     * projectPhone : 15884724862;
     * projectId : 1540783343869342
     */

    private int alarmTotalCount;
    private String projectArea;
    private String projectLeader;
    private Object pType;
    private String diff;
    private int alarmDeviceCount;
    private String projectName;
    private String projectPhone;
    private String projectId;

    public int getAlarmTotalCount() {
        return alarmTotalCount;
    }

    public void setAlarmTotalCount(int alarmTotalCount) {
        this.alarmTotalCount = alarmTotalCount;
    }

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

    public int getAlarmDeviceCount() {
        return alarmDeviceCount;
    }

    public void setAlarmDeviceCount(int alarmDeviceCount) {
        this.alarmDeviceCount = alarmDeviceCount;
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
