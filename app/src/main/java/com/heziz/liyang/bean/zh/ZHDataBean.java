package com.heziz.liyang.bean.zh;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-08.
 * from:
 * describe:
 */

public class ZHDataBean implements Serializable {

    /**
     * WeatherOfflineCount : 2
     * WeatherOnlineCount : 0
     * VideoOfflineCount : 1
     * VideoOnlineCount : 1
     * VideoUnknowCount : 0
     * FtpDeviceCount : 1
     */

    private String WeatherOfflineCount;
    private String WeatherOnlineCount;
    private String VideoOfflineCount;
    private String VideoOnlineCount;
    private String VideoUnknowCount;
    private String FtpDeviceCount;

    public String getWeatherOfflineCount() {
        return WeatherOfflineCount;
    }

    public void setWeatherOfflineCount(String WeatherOfflineCount) {
        this.WeatherOfflineCount = WeatherOfflineCount;
    }

    public String getWeatherOnlineCount() {
        return WeatherOnlineCount;
    }

    public void setWeatherOnlineCount(String WeatherOnlineCount) {
        this.WeatherOnlineCount = WeatherOnlineCount;
    }

    public String getVideoOfflineCount() {
        return VideoOfflineCount;
    }

    public void setVideoOfflineCount(String VideoOfflineCount) {
        this.VideoOfflineCount = VideoOfflineCount;
    }

    public String getVideoOnlineCount() {
        return VideoOnlineCount;
    }

    public void setVideoOnlineCount(String VideoOnlineCount) {
        this.VideoOnlineCount = VideoOnlineCount;
    }

    public String getVideoUnknowCount() {
        return VideoUnknowCount;
    }

    public void setVideoUnknowCount(String VideoUnknowCount) {
        this.VideoUnknowCount = VideoUnknowCount;
    }

    public String getFtpDeviceCount() {
        return FtpDeviceCount;
    }

    public void setFtpDeviceCount(String FtpDeviceCount) {
        this.FtpDeviceCount = FtpDeviceCount;
    }
}
