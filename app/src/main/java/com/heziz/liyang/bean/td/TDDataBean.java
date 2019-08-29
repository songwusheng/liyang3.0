package com.heziz.liyang.bean.td;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sws on 2019-08-15.
 * from:
 * describe:
 */

public class TDDataBean implements Serializable {

    /**
     * id : 1540783343868729
     * name : 凤凰花园二期——1~3#住宅、11~12#住宅、商业A段及地下室工程
     * area : 江苏 常州 溧阳 溧城镇
     * online : 1
     * offline : 2
     * count : 3
     * towers : [{"deviceName":"凤凰花园二期1#住宅","deviceNum":"18111630","state":0,"towerBrachialArm":"12","towerForearm":"50","towerPagoda":"38"},{"deviceName":"凤凰花园二期2#住宅","deviceNum":"18111631","state":1,"towerBrachialArm":"12","towerForearm":"50","towerPagoda":"38"},{"deviceName":"凤凰花园二期3#住宅","deviceNum":"18111632","state":0,"towerBrachialArm":"12","towerForearm":"50","towerPagoda":"38"}]
     */

    private long id;
    private String name;
    private String area;
    private int online;
    private int offline;
    private int count;
    private List<TowersBean> towers;

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

    public List<TowersBean> getTowers() {
        return towers;
    }

    public void setTowers(List<TowersBean> towers) {
        this.towers = towers;
    }

    public static class TowersBean {
        /**
         * deviceName : 凤凰花园二期1#住宅
         * deviceNum : 18111630
         * state : 0
         * towerBrachialArm : 12
         * towerForearm : 50
         * towerPagoda : 38
         */

        private String deviceName;
        private String deviceNum;
        private int state;
        private String towerBrachialArm;
        private String towerForearm;
        private String towerPagoda;

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTowerBrachialArm() {
            return towerBrachialArm;
        }

        public void setTowerBrachialArm(String towerBrachialArm) {
            this.towerBrachialArm = towerBrachialArm;
        }

        public String getTowerForearm() {
            return towerForearm;
        }

        public void setTowerForearm(String towerForearm) {
            this.towerForearm = towerForearm;
        }

        public String getTowerPagoda() {
            return towerPagoda;
        }

        public void setTowerPagoda(String towerPagoda) {
            this.towerPagoda = towerPagoda;
        }
    }
}
