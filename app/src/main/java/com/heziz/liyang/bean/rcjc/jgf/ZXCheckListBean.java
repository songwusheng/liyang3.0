package com.heziz.liyang.bean.rcjc.jgf;

import java.io.Serializable;

/**
 * Created by sws on 2019-12-09.
 * from:
 * describe:
 */

public class ZXCheckListBean implements Serializable{

    /**
     * id : 444
     * projectId : 1574643879542035
     * projectName : 2019G76地块
     * siteId : 1551258680345413
     * managerRoleIds : [1552292505892246]
     * checkAccount : qx19001@wj
     * checkName : 联通:王婕
     * startTime : 2019-11-25 09:40:52
     * type : null
     * pass : null
     * eventName : 道路保洁
     * endStatus : 2
     */

    private int id;
    private long projectId;
    private String projectName;
    private String siteId;
    private String managerRoleIds;
    private String checkAccount;
    private String checkName;
    private String startTime;
    private Object type;
    private Object pass;
    private String eventName;
    private int endStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getManagerRoleIds() {
        return managerRoleIds;
    }

    public void setManagerRoleIds(String managerRoleIds) {
        this.managerRoleIds = managerRoleIds;
    }

    public String getCheckAccount() {
        return checkAccount;
    }

    public void setCheckAccount(String checkAccount) {
        this.checkAccount = checkAccount;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getPass() {
        return pass;
    }

    public void setPass(Object pass) {
        this.pass = pass;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(int endStatus) {
        this.endStatus = endStatus;
    }
}
