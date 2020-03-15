package com.heziz.liyang.bean.rcjc;

import java.util.List;

/**
 * Created by sws on 2019-11-25.
 * from:
 * describe:
 */

public class NewCheckBean {
    private String name;
    private String describe;
    private String xjjg;
    private List<String> imgList;
    private String status;
    private String type;
    private int picNum;

    public NewCheckBean(String type,String name, String status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getXjjg() {
        return xjjg;
    }

    public void setXjjg(String xjjg) {
        this.xjjg = xjjg;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
