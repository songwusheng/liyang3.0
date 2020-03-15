package com.heziz.liyang.bean.rcjc.jgf;

/**
 * Created by sws on 2019-12-09.
 * from:
 * describe:
 */

public class ZXCheckSHBean {

    /**
     * checkAccount :
     * checkComments :
     * checkTime : 复查时间
     * endStatus : 1
     * id : 111
     */

    private String checkAccount;
    private String checkComments;
    private String checkTime;
    private int endStatus;
    private int id;

    public ZXCheckSHBean(String checkAccount, String checkComments, String checkTime, int endStatus, int id) {
        this.checkAccount = checkAccount;
        this.checkComments = checkComments;
        this.checkTime = checkTime;
        this.endStatus = endStatus;
        this.id = id;
    }

    public String getCheckAccount() {
        return checkAccount;
    }

    public void setCheckAccount(String checkAccount) {
        this.checkAccount = checkAccount;
    }

    public String getCheckComments() {
        return checkComments;
    }

    public void setCheckComments(String checkComments) {
        this.checkComments = checkComments;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public int getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(int endStatus) {
        this.endStatus = endStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
