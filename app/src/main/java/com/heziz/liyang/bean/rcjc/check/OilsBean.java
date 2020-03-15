package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class OilsBean implements Serializable {

    /**
     * oilsAccount : qx19001
     * oilsCommonts :
     * oilsImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100502.jpg
     * oilsStatus : 1
     * oilsTime : 2019-11-2710:05:17
     */

    private String oilsAccount;
    private String oilsCommonts;
    private String oilsImage;
    private int oilsStatus;
    private String oilsTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getOilsAccount() {
        return oilsAccount;
    }

    public void setOilsAccount(String oilsAccount) {
        this.oilsAccount = oilsAccount;
    }

    public String getOilsCommonts() {
        return oilsCommonts;
    }

    public void setOilsCommonts(String oilsCommonts) {
        this.oilsCommonts = oilsCommonts;
    }

    public String getOilsImage() {
        return oilsImage;
    }

    public void setOilsImage(String oilsImage) {
        this.oilsImage = oilsImage;
    }

    public int getOilsStatus() {
        return oilsStatus;
    }

    public void setOilsStatus(int oilsStatus) {
        this.oilsStatus = oilsStatus;
    }

    public String getOilsTime() {
        return oilsTime;
    }

    public void setOilsTime(String oilsTime) {
        this.oilsTime = oilsTime;
    }
}
