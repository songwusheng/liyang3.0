package com.heziz.liyang.bean;

import java.io.Serializable;

/**
 * Created by sws on 2018/5/9.
 * from:
 * describe:
 */

public class VProjectBean implements Serializable {


    /**
     * login_port : 9000
     * platform_id : null
     * username : ty1
     * create_date : 2018-08-15 14:52:01
     * media_port : 1000033$home_bg_icon1$0$0
     * name : 山西绿色开采装备技术研发院项目一期科研实验楼A栋
     * update_date : 2018-08-15 14:52:35
     * camera_id : null
     * remarks : null
     * password : admin123
     * pass : home_bg_icon1
     * ip : 112.85.138.10
     */

    private String login_port;
    private Object platform_id;
    private String username;
    private String create_date;
    private String media_port;
    private String name;
    private String update_date;
    private Object camera_id;
    private Object remarks;
    private String password;
    private String pass;
    private String ip;

    public String getLogin_port() {
        return login_port;
    }

    public void setLogin_port(String login_port) {
        this.login_port = login_port;
    }

    public Object getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(Object platform_id) {
        this.platform_id = platform_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getMedia_port() {
        return media_port;
    }

    public void setMedia_port(String media_port) {
        this.media_port = media_port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public Object getCamera_id() {
        return camera_id;
    }

    public void setCamera_id(Object camera_id) {
        this.camera_id = camera_id;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "VProjectBean{" +
                "login_port='" + login_port + '\'' +
                ", platform_id=" + platform_id +
                ", username='" + username + '\'' +
                ", create_date='" + create_date + '\'' +
                ", media_port='" + media_port + '\'' +
                ", name='" + name + '\'' +
                ", update_date='" + update_date + '\'' +
                ", camera_id=" + camera_id +
                ", remarks=" + remarks +
                ", password='" + password + '\'' +
                ", pass='" + pass + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
