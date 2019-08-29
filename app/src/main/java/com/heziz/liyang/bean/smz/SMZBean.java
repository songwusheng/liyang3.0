package com.heziz.liyang.bean.smz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sws on 2019-08-14.
 * from:
 * describe:
 */

public class SMZBean implements Serializable {

    /**
     * id : 1540783343868716
     * name : 银江国际
     * area : 江苏 常州 溧阳 燕山新区
     * online : 2
     * offline : 0
     * count : 2
     * devices : [{"deviceName":"银江国际进口","deviceNo":"8128317120006701","devicepurpose":"0","state":1},{"deviceName":"银江国际出口","deviceNo":"8128317120006702","devicepurpose":"1","state":1}]
     */

    private long id;
    private String name;
    private String area;
    private int online;
    private int offline;
    private int count;
    private List<DevicesBean> devices;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DevicesBean> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesBean> devices) {
        this.devices = devices;
    }

    public static class DevicesBean {
        /**
         * deviceName : 银江国际进口
         * deviceNo : 8128317120006701
         * devicepurpose : 0
         * state : 1
         */

        private String deviceName;
        private String deviceNo;
        private String devicepurpose;
        private int state;

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getDevicepurpose() {
            return devicepurpose;
        }

        public void setDevicepurpose(String devicepurpose) {
            this.devicepurpose = devicepurpose;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
