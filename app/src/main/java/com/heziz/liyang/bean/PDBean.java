package com.heziz.liyang.bean;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class PDBean {

    /**
     * ftpAlarmCount : 0
     * videoNum : 0
     * weatherAlarmNum : 0
     */

    private String ftpAlarmCount;
    private String videoNum;
    private String weatherAlarmNum;

    public String getFtpAlarmCount() {
        return ftpAlarmCount;
    }

    public void setFtpAlarmCount(String ftpAlarmCount) {
        this.ftpAlarmCount = ftpAlarmCount;
    }

    public String getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(String videoNum) {
        this.videoNum = videoNum;
    }

    public String getWeatherAlarmNum() {
        return weatherAlarmNum;
    }

    public void setWeatherAlarmNum(String weatherAlarmNum) {
        this.weatherAlarmNum = weatherAlarmNum;
    }
}
