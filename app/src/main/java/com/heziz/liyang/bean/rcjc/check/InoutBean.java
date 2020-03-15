package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class InoutBean implements Serializable {

    /**
     * inoutAccount : qx19001
     * inoutCommonts :
     * inoutImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100458.jpg
     * inoutStatus : 2
     * inoutTime : 2019-11-2710:05:17
     */

    private String inoutAccount;
    private String inoutCommonts;
    private String inoutImage;
    private int inoutStatus;
    private String inoutTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getInoutAccount() {
        return inoutAccount;
    }

    public void setInoutAccount(String inoutAccount) {
        this.inoutAccount = inoutAccount;
    }

    public String getInoutCommonts() {
        return inoutCommonts;
    }

    public void setInoutCommonts(String inoutCommonts) {
        this.inoutCommonts = inoutCommonts;
    }

    public String getInoutImage() {
        return inoutImage;
    }

    public void setInoutImage(String inoutImage) {
        this.inoutImage = inoutImage;
    }

    public int getInoutStatus() {
        return inoutStatus;
    }

    public void setInoutStatus(int inoutStatus) {
        this.inoutStatus = inoutStatus;
    }

    public String getInoutTime() {
        return inoutTime;
    }

    public void setInoutTime(String inoutTime) {
        this.inoutTime = inoutTime;
    }
}
