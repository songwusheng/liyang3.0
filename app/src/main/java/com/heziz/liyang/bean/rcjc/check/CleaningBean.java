package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class CleaningBean implements Serializable {

    /**
     * cleaningAccount : qx19001
     * cleaningCommonts :
     * cleaningImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无 20191127100443.jpg,https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt 2_20191104064031861_无车牌_无20191127100444.jpg
     * cleaningStatus : 1
     * cleaningTime : 2019-11-2710:05:17
     */

    private String cleaningAccount;
    private String cleaningCommonts;
    private String cleaningImage;
    private int cleaningStatus;
    private String cleaningTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getCleaningAccount() {
        return cleaningAccount;
    }

    public void setCleaningAccount(String cleaningAccount) {
        this.cleaningAccount = cleaningAccount;
    }

    public String getCleaningCommonts() {
        return cleaningCommonts;
    }

    public void setCleaningCommonts(String cleaningCommonts) {
        this.cleaningCommonts = cleaningCommonts;
    }

    public String getCleaningImage() {
        return cleaningImage;
    }

    public void setCleaningImage(String cleaningImage) {
        this.cleaningImage = cleaningImage;
    }

    public int getCleaningStatus() {
        return cleaningStatus;
    }

    public void setCleaningStatus(int cleaningStatus) {
        this.cleaningStatus = cleaningStatus;
    }

    public String getCleaningTime() {
        return cleaningTime;
    }

    public void setCleaningTime(String cleaningTime) {
        this.cleaningTime = cleaningTime;
    }
}
