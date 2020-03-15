package com.heziz.liyang.bean.rcjc.jgf;

import java.io.Serializable;

/**
 * Created by sws on 2019-12-09.
 * from:
 * describe:
 */

public class NewZXCheckBean implements Serializable {

    /**
     * customEventCommonts :
     * customEventImage :
     * customEventName : 检查事项的名称
     * customEventTime : 检查事项的时间
     * endStatus : 2合格，4不合格
     * projectId : 必填
     * startPopedomAccount : 当前人登录人账户
     * customEventType
     */

    private String customEventCommonts;
    private String customEventImage;
    private String customEventName;
    private String customEventTime;
    private String endStatus;
    private String projectId;
    private String startPopedomAccount;
    private String customEventType;

    public String getCustomEventType() {
        return customEventType;
    }

    public void setCustomEventType(String customEventType) {
        this.customEventType = customEventType;
    }

    public String getCustomEventCommonts() {
        return customEventCommonts;
    }

    public void setCustomEventCommonts(String customEventCommonts) {
        this.customEventCommonts = customEventCommonts;
    }

    public String getCustomEventImage() {
        return customEventImage;
    }

    public void setCustomEventImage(String customEventImage) {
        this.customEventImage = customEventImage;
    }

    public String getCustomEventName() {
        return customEventName;
    }

    public void setCustomEventName(String customEventName) {
        this.customEventName = customEventName;
    }

    public String getCustomEventTime() {
        return customEventTime;
    }

    public void setCustomEventTime(String customEventTime) {
        this.customEventTime = customEventTime;
    }

    public String getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(String endStatus) {
        this.endStatus = endStatus;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStartPopedomAccount() {
        return startPopedomAccount;
    }

    public void setStartPopedomAccount(String startPopedomAccount) {
        this.startPopedomAccount = startPopedomAccount;
    }
}
