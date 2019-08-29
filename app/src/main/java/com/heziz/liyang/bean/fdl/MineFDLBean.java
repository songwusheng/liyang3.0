package com.heziz.liyang.bean.fdl;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-30.
 * from:
 * describe:
 */

public class MineFDLBean implements Serializable {

    /**
     * id : 120
     * createBy : 1555758079463270
     * createTime : 2019-05-08 17:17:06
     * updateBy : null
     * updateTime : 2019-05-08 17:21:09
     * projectName : 低温余热发电机组一期项目
     * auditorName : 栖霞
     * projectId : 1555758080783712
     * amount : 324
     * grade : 国三
     * auditStatus : 1
     * auditorId : 1552292505952919
     * invoicePath : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/05/08/3223432_173334896000_22019043016093420190508171704.jpg
     * auditTime : 1557307269000
     * siteId : 1551258680345413
     * managerId : [1552292505892246],[1551258680347513]
     * remark : 吧
     * batch : 1
     * byTime : 2019-05-08 00:00:00
     */

    private int id;
    private long createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private String projectName;
    private String auditorName;
    private long projectId;
    private double amount;
    private String grade;
    private int auditStatus;
    private long auditorId;
    private String invoicePath;
    private long auditTime;
    private String siteId;
    private String managerId;
    private String remark;
    private int batch;
    private String byTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(long auditorId) {
        this.auditorId = auditorId;
    }

    public String getInvoicePath() {
        return invoicePath;
    }

    public void setInvoicePath(String invoicePath) {
        this.invoicePath = invoicePath;
    }

    public long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(long auditTime) {
        this.auditTime = auditTime;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
