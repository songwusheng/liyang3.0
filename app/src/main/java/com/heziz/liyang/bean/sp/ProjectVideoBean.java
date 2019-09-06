package com.heziz.liyang.bean.sp;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-10.
 * from:
 * describe:
 */

public class ProjectVideoBean<T> implements Serializable {

    /**
     * id : 1556446186026568
     * createBy : 1
     * createTime : 2019-04-28 18:09:46
     * updateBy : 1
     * updateTime : 2019-04-30 17:35:20
     * videoDeviceId : null
     * devicename : 上兴保障房
     * isOnline : Offline
     * area : null
     * videoDeviceAddress :
     * videoDeviceUserName :
     * videoDevicePassword :
     * videoDeviceLoginPort :
     * videoDeviceLevePort :
     * videoDeviceEndPoint : 1541992240945678
     * cameraid : null
     * channelnum : null
     * substream : null
     * userid : null
     * delFlag : null
     * mVideoProjectDevice :
     */

    private long id;
    private long createBy;
    private String createTime;
    private Long updateBy;
    private String updateTime;
    private Object videoDeviceId;
    private String devicename;
    private String isOnline;
    private Object area;
    private String videoDeviceAddress;
    private String videoDeviceUserName;
    private String videoDevicePassword;
    private String videoDeviceLoginPort;
    private String videoDeviceLevePort;
    private String videoDeviceEndPoint;
    private Object cameraid;
    private Object channelnum;
    private Object substream;
    private Object userid;
    private Object delFlag;
    private T mVideoProjectDevice;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public Object getVideoDeviceId() {
        return videoDeviceId;
    }

    public void setVideoDeviceId(Object videoDeviceId) {
        this.videoDeviceId = videoDeviceId;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public String getVideoDeviceAddress() {
        return videoDeviceAddress;
    }

    public void setVideoDeviceAddress(String videoDeviceAddress) {
        this.videoDeviceAddress = videoDeviceAddress;
    }

    public String getVideoDeviceUserName() {
        return videoDeviceUserName;
    }

    public void setVideoDeviceUserName(String videoDeviceUserName) {
        this.videoDeviceUserName = videoDeviceUserName;
    }

    public String getVideoDevicePassword() {
        return videoDevicePassword;
    }

    public void setVideoDevicePassword(String videoDevicePassword) {
        this.videoDevicePassword = videoDevicePassword;
    }

    public String getVideoDeviceLoginPort() {
        return videoDeviceLoginPort;
    }

    public void setVideoDeviceLoginPort(String videoDeviceLoginPort) {
        this.videoDeviceLoginPort = videoDeviceLoginPort;
    }

    public String getVideoDeviceLevePort() {
        return videoDeviceLevePort;
    }

    public void setVideoDeviceLevePort(String videoDeviceLevePort) {
        this.videoDeviceLevePort = videoDeviceLevePort;
    }

    public String getVideoDeviceEndPoint() {
        return videoDeviceEndPoint;
    }

    public void setVideoDeviceEndPoint(String videoDeviceEndPoint) {
        this.videoDeviceEndPoint = videoDeviceEndPoint;
    }

    public Object getCameraid() {
        return cameraid;
    }

    public void setCameraid(Object cameraid) {
        this.cameraid = cameraid;
    }

    public Object getChannelnum() {
        return channelnum;
    }

    public void setChannelnum(Object channelnum) {
        this.channelnum = channelnum;
    }

    public Object getSubstream() {
        return substream;
    }

    public void setSubstream(Object substream) {
        this.substream = substream;
    }

    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
        this.userid = userid;
    }

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public T getMVideoProjectDevice() {
        return mVideoProjectDevice;
    }

    public void setMVideoProjectDevice(T mVideoProjectDevice) {
        this.mVideoProjectDevice = mVideoProjectDevice;
    }
}
