package com.heziz.liyang.bean.sp;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-10.
 * from:
 * describe:
 */

public class VideoProjectBean implements Serializable {

    /**
     * id : 1556446186041377
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * videoDeviceId : 1556446186026568
     * projectId : 1540783343868718
     * projectIdlist : null
     * delFlag : null
     * type : 2
     * url :
     * model : 0
     * cameraid : 1000177
     * channelnum : 0
     * substream : 1
     */

    private long id;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private long videoDeviceId;
    private String projectId;
    private Object projectIdlist;
    private Object delFlag;
    private String type;
    private String url;
    private String model;
    private String cameraid;
    private String channelnum;
    private String substream;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getVideoDeviceId() {
        return videoDeviceId;
    }

    public void setVideoDeviceId(long videoDeviceId) {
        this.videoDeviceId = videoDeviceId;
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

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCameraid() {
        return cameraid;
    }

    public void setCameraid(String cameraid) {
        this.cameraid = cameraid;
    }

    public String getChannelnum() {
        return channelnum;
    }

    public void setChannelnum(String channelnum) {
        this.channelnum = channelnum;
    }

    public String getSubstream() {
        return substream;
    }

    public void setSubstream(String substream) {
        this.substream = substream;
    }
}
