package com.heziz.liyang.bean.sjj;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sws on 2019-09-17.
 * from:
 * describe:
 */

public class SjjProjectBean implements Serializable {

    /**
     * id : 1540783343868791
     * name : 南大街西侧12号地块（银江国际龙海项目部）
     * area : 江苏 常州 溧阳 燕山新区
     * online : 1
     * offline : 0
     * count : 1
     * towers : [{"deviceName":null,"deviceNum":"9000006","state":1,"towerBrachialArm":null,"towerForearm":null,"towerPagoda":null}]
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
         * deviceName : null
         * deviceNum : 9000006
         * state : 1
         * towerBrachialArm : null
         * towerForearm : null
         * towerPagoda : null
         */

        private Object deviceName;
        private String deviceNum;
        private int state;
        private Object towerBrachialArm;
        private Object towerForearm;
        private Object towerPagoda;

        public Object getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(Object deviceName) {
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

        public Object getTowerBrachialArm() {
            return towerBrachialArm;
        }

        public void setTowerBrachialArm(Object towerBrachialArm) {
            this.towerBrachialArm = towerBrachialArm;
        }

        public Object getTowerForearm() {
            return towerForearm;
        }

        public void setTowerForearm(Object towerForearm) {
            this.towerForearm = towerForearm;
        }

        public Object getTowerPagoda() {
            return towerPagoda;
        }

        public void setTowerPagoda(Object towerPagoda) {
            this.towerPagoda = towerPagoda;
        }
    }
}
