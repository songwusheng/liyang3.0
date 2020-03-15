package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class EngineBean implements Serializable {

    /**
     * engineAccount : qx19001
     * engineCommonts :
     * engineImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100507.jpg
     * engineStatus : 2
     * engineTime : 2019-11-2710:05:17
     */

    private String engineAccount;
    private String engineCommonts;
    private String engineImage;
    private int engineStatus;
    private String engineTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getEngineAccount() {
        return engineAccount;
    }

    public void setEngineAccount(String engineAccount) {
        this.engineAccount = engineAccount;
    }

    public String getEngineCommonts() {
        return engineCommonts;
    }

    public void setEngineCommonts(String engineCommonts) {
        this.engineCommonts = engineCommonts;
    }

    public String getEngineImage() {
        return engineImage;
    }

    public void setEngineImage(String engineImage) {
        this.engineImage = engineImage;
    }

    public int getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(int engineStatus) {
        this.engineStatus = engineStatus;
    }

    public String getEngineTime() {
        return engineTime;
    }

    public void setEngineTime(String engineTime) {
        this.engineTime = engineTime;
    }
}
