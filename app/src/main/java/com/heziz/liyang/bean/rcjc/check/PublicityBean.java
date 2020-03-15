package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class PublicityBean implements Serializable {

    /**
     * publicityAccount : qx19001
     * publicityCommonts :
     * publicityImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100510.jpg
     * publicityStatus : 1
     * publicityTime : 2019-11-2710:05:17
     */

    private String publicityAccount;
    private String publicityCommonts;
    private String publicityImage;
    private int publicityStatus;
    private String publicityTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getPublicityAccount() {
        return publicityAccount;
    }

    public void setPublicityAccount(String publicityAccount) {
        this.publicityAccount = publicityAccount;
    }

    public String getPublicityCommonts() {
        return publicityCommonts;
    }

    public void setPublicityCommonts(String publicityCommonts) {
        this.publicityCommonts = publicityCommonts;
    }

    public String getPublicityImage() {
        return publicityImage;
    }

    public void setPublicityImage(String publicityImage) {
        this.publicityImage = publicityImage;
    }

    public int getPublicityStatus() {
        return publicityStatus;
    }

    public void setPublicityStatus(int publicityStatus) {
        this.publicityStatus = publicityStatus;
    }

    public String getPublicityTime() {
        return publicityTime;
    }

    public void setPublicityTime(String publicityTime) {
        this.publicityTime = publicityTime;
    }
}
