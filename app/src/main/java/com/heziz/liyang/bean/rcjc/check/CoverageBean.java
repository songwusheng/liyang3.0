package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class CoverageBean implements Serializable {

    /**
     * coverageAccount : qx19001
     * coverageCommonts :
     * coverageImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100448.jpg
     * coverageStatus : 1
     * coverageTime : 2019-11-2710:05:17
     */

    private String coverageAccount;
    private String coverageCommonts;
    private String coverageImage;
    private int coverageStatus;
    private String coverageTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getCoverageAccount() {
        return coverageAccount;
    }

    public void setCoverageAccount(String coverageAccount) {
        this.coverageAccount = coverageAccount;
    }

    public String getCoverageCommonts() {
        return coverageCommonts;
    }

    public void setCoverageCommonts(String coverageCommonts) {
        this.coverageCommonts = coverageCommonts;
    }

    public String getCoverageImage() {
        return coverageImage;
    }

    public void setCoverageImage(String coverageImage) {
        this.coverageImage = coverageImage;
    }

    public int getCoverageStatus() {
        return coverageStatus;
    }

    public void setCoverageStatus(int coverageStatus) {
        this.coverageStatus = coverageStatus;
    }

    public String getCoverageTime() {
        return coverageTime;
    }

    public void setCoverageTime(String coverageTime) {
        this.coverageTime = coverageTime;
    }
}
