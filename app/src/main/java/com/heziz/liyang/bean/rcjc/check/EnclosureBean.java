package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class EnclosureBean implements Serializable {

    /**
     * enclosureAccount : qx19001
     * enclosureCommonts :
     * enclosureImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100451.jpg
     * enclosureStatus : 2
     * enclosureTime : 2019-11-2710:05:17
     */

    private String enclosureAccount;
    private String enclosureCommonts;
    private String enclosureImage;
    private int enclosureStatus;
    private String enclosureTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getEnclosureAccount() {
        return enclosureAccount;
    }

    public void setEnclosureAccount(String enclosureAccount) {
        this.enclosureAccount = enclosureAccount;
    }

    public String getEnclosureCommonts() {
        return enclosureCommonts;
    }

    public void setEnclosureCommonts(String enclosureCommonts) {
        this.enclosureCommonts = enclosureCommonts;
    }

    public String getEnclosureImage() {
        return enclosureImage;
    }

    public void setEnclosureImage(String enclosureImage) {
        this.enclosureImage = enclosureImage;
    }

    public int getEnclosureStatus() {
        return enclosureStatus;
    }

    public void setEnclosureStatus(int enclosureStatus) {
        this.enclosureStatus = enclosureStatus;
    }

    public String getEnclosureTime() {
        return enclosureTime;
    }

    public void setEnclosureTime(String enclosureTime) {
        this.enclosureTime = enclosureTime;
    }
}
