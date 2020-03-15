package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class HardeningBean implements Serializable {

    /**
     * hardeningAccount : qx19001
     * hardeningCommonts :
     * hardeningImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100455.jpg
     * hardeningStatus : 2
     * hardeningTime : 2019-11-2710:05:17
     */

    private String hardeningAccount;
    private String hardeningCommonts;
    private String hardeningImage;
    private int hardeningStatus;
    private String hardeningTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getHardeningAccount() {
        return hardeningAccount;
    }

    public void setHardeningAccount(String hardeningAccount) {
        this.hardeningAccount = hardeningAccount;
    }

    public String getHardeningCommonts() {
        return hardeningCommonts;
    }

    public void setHardeningCommonts(String hardeningCommonts) {
        this.hardeningCommonts = hardeningCommonts;
    }

    public String getHardeningImage() {
        return hardeningImage;
    }

    public void setHardeningImage(String hardeningImage) {
        this.hardeningImage = hardeningImage;
    }

    public int getHardeningStatus() {
        return hardeningStatus;
    }

    public void setHardeningStatus(int hardeningStatus) {
        this.hardeningStatus = hardeningStatus;
    }

    public String getHardeningTime() {
        return hardeningTime;
    }

    public void setHardeningTime(String hardeningTime) {
        this.hardeningTime = hardeningTime;
    }
}
