package com.heziz.liyang.bean.fdl;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-17.
 * from:
 * describe:
 */

public class FdlProjectDetailsBean implements Serializable {

    /**
     * amount : 0
     * auditStatus : 0
     * auditTime :
     * auditorId : 0
     * batch : 0
     * byTime : 2018-10-10 15:21:21
     * createBy : 0
     * createTime :
     * endTime : 2018-10-10 15:21:21
     * grade : 国5
     * id : 0
     * invoicePath :
     * managerId :
     * projectId : 0
     * remark :
     * siteId :
     * startTime : 2018-10-10 15:21:21
     * updateBy : 0
     * updateTime :
     */

    private double amount;
    private int auditStatus;
    private String auditTime;
    private Long auditorId;
    private int batch;
    private String byTime;
    private Long createBy;
    private String createTime;
    private String endTime;
    private String grade;
    private int id;
    private String invoicePath;
    private String managerId;
    private Long projectId;
    private String remark;
    private String siteId;
    private String startTime;
    private Long updateBy;
    private String updateTime;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getByTime() {
        return byTime;
    }

    public void setByTime(String byTime) {
        this.byTime = byTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoicePath() {
        return invoicePath;
    }

    public void setInvoicePath(String invoicePath) {
        this.invoicePath = invoicePath;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
