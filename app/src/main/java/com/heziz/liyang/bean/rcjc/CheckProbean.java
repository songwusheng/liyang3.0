package com.heziz.liyang.bean.rcjc;

import java.io.Serializable;

/**
 * Created by sws on 2019-11-28.
 * from:
 * describe:
 */

public class CheckProbean implements Serializable {

    /**
     * projectId : 1540783343869307
     * projectName : 中国电子科技集团第二十八研究所仙林新所区
     * managerRoleIds : [1552294127929222],[1551258680347513]
     * station : 1551258680345413
     * projectAdress
     * changeProjectAccount
     * createName
     */

    private String projectId;
    private String projectName;
    private String managerRoleIds;
    private String station;
    private String projectAdress;
    private String changeProjectAccount;
    private String createName;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getProjectAdress() {
        return projectAdress;
    }

    public void setProjectAdress(String projectAdress) {
        this.projectAdress = projectAdress;
    }

    public String getChangeProjectAccount() {
        return changeProjectAccount;
    }

    public void setChangeProjectAccount(String changeProjectAccount) {
        this.changeProjectAccount = changeProjectAccount;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManagerRoleIds() {
        return managerRoleIds;
    }

    public void setManagerRoleIds(String managerRoleIds) {
        this.managerRoleIds = managerRoleIds;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
