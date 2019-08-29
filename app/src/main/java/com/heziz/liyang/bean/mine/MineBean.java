package com.heziz.liyang.bean.mine;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-28.
 * from:
 * describe:
 */

public class MineBean implements Serializable {

    /**
     * weatherAlarmCount : 5
     * ftpAlarmCount : 1981
     */

    private String weatherAlarmCount;
    private String ftpAlarmCount;

    public String getWeatherAlarmCount() {
        return weatherAlarmCount;
    }

    public void setWeatherAlarmCount(String weatherAlarmCount) {
        this.weatherAlarmCount = weatherAlarmCount;
    }

    public String getFtpAlarmCount() {
        return ftpAlarmCount;
    }

    public void setFtpAlarmCount(String ftpAlarmCount) {
        this.ftpAlarmCount = ftpAlarmCount;
    }
}
