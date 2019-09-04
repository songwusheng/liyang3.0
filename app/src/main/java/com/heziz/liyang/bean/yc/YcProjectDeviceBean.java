package com.heziz.liyang.bean.yc;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class YcProjectDeviceBean implements Serializable{

    /**
     * id : 1556503888323996
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * weacherDeviceId : 1556503888314802
     * projectId : 1556503046625548
     * projectIdlist : null
     * videoid : null
     * weacher_device_id : null
     * delFlag : null
     * offlinetime : 5
     */

    private long id;
    private long createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private long weacherDeviceId;
    private String projectId;
    private Object projectIdlist;
    private Object videoid;
    private Object weacher_device_id;
    private Object delFlag;
    private String offlinetime;

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

    public long getWeacherDeviceId() {
        return weacherDeviceId;
    }

    public void setWeacherDeviceId(long weacherDeviceId) {
        this.weacherDeviceId = weacherDeviceId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Object getProjectIdlist() {
        return projectIdlist;
    }

    public void setProjectIdlist(Object projectIdlist) {
        this.projectIdlist = projectIdlist;
    }

    public Object getVideoid() {
        return videoid;
    }

    public void setVideoid(Object videoid) {
        this.videoid = videoid;
    }

    public Object getWeacher_device_id() {
        return weacher_device_id;
    }

    public void setWeacher_device_id(Object weacher_device_id) {
        this.weacher_device_id = weacher_device_id;
    }

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public String getOfflinetime() {
        return offlinetime;
    }

    public void setOfflinetime(String offlinetime) {
        this.offlinetime = offlinetime;
    }
}
