package com.heziz.liyang.bean.mine.clcx;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-30.
 * from:
 * describe:
 */

public class MineCLCXListBean implements Serializable {

    /**
     * dateTime : 2019-05-30T00:24:31.523Z
     * path : /var/log/messages
     * licenseType : -1
     * singId : 20190530082431523
     * licensePlate : 苏AV3320
     * @timestamp : 2019-05-30T00:22:08.838Z
     * @version : 1
     * host : izuf6hy50lm0nck5vorzwoz
     * projectName : jblew
     * licensePlateImg : /jblew/1/jblew_20190530082431523_苏AV3320_黄.jpg
     * licensePlateColor : 黄
     */

    private String dateTime;
    private String path;
    private String licenseType;
    private String singId;
    private String licensePlate;
    @SerializedName("@timestamp")
    private String _$Timestamp238; // FIXME check this code
    @SerializedName("@version")
    private String _$Version313; // FIXME check this code
    private String host;
    private String projectName;
    private String licensePlateImg;
    private String licensePlateColor;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getSingId() {
        return singId;
    }

    public void setSingId(String singId) {
        this.singId = singId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String get_$Timestamp238() {
        return _$Timestamp238;
    }

    public void set_$Timestamp238(String _$Timestamp238) {
        this._$Timestamp238 = _$Timestamp238;
    }

    public String get_$Version313() {
        return _$Version313;
    }

    public void set_$Version313(String _$Version313) {
        this._$Version313 = _$Version313;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLicensePlateImg() {
        return licensePlateImg;
    }

    public void setLicensePlateImg(String licensePlateImg) {
        this.licensePlateImg = licensePlateImg;
    }

    public String getLicensePlateColor() {
        return licensePlateColor;
    }

    public void setLicensePlateColor(String licensePlateColor) {
        this.licensePlateColor = licensePlateColor;
    }
}
