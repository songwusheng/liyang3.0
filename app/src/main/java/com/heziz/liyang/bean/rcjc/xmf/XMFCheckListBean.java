package com.heziz.liyang.bean.rcjc.xmf;

import java.io.Serializable;

/**
 * Created by sws on 2019-12-06.
 * from:
 * describe:
 */

public class XMFCheckListBean implements Serializable {

    /**
     * id : 914
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * projectId : 1540783343869307
     * projectName : 中国电子科技集团第二十八研究所仙林新所区
     * siteId : 1551258680345413
     * managerRoleIds : [1552294127929222],[1551258680347513]
     * startTime : 2019-07-06 09:10:02
     * endTime : 2019-07-06 09:13:56
     * projectAccount : qx19038
     * projectLeaderName : 吕京涛
     * endStatus : 2
     * type : null
     * pass : null
     * popedomName
     */

    private int id;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private long projectId;
    private String projectName;
    private String siteId;
    private String managerRoleIds;
    private String startTime;
    private String endTime;
    private String projectAccount;
    private String projectLeaderName;
    private String popedomName  ;
    private int endStatus;
    private Object type;
    private Object pass;

    public String getPopedomName() {
        return popedomName;
    }

    public void setPopedomName(String popedomName) {
        this.popedomName = popedomName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProjectAccount() {
        return projectAccount;
    }

    public void setProjectAccount(String projectAccount) {
        this.projectAccount = projectAccount;
    }

    public String getProjectLeaderName() {
        return projectLeaderName;
    }

    public void setProjectLeaderName(String projectLeaderName) {
        this.projectLeaderName = projectLeaderName;
    }

    public int getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(int endStatus) {
        this.endStatus = endStatus;
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
}
