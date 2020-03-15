package com.heziz.liyang.bean.rcjc.check;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sws on 2019-11-29.
 * from:
 * describe:
 */

public class TransportBean implements Serializable {

    /**
     * transportAccount : qx19001
     * transportCommonts :
     * transportImage : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/11/27/jbnjxt2_20191104064031 861_无车牌_无20191127100508.jpg
     * transportStatus : 1
     * transportTime : 2019-11-2710:05:17
     */

    private String transportAccount;
    private String transportCommonts;
    private String transportImage;
    private int transportStatus;
    private String transportTime;
    private ArrayList<ImageItem> selImageList;

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public String getTransportAccount() {
        return transportAccount;
    }

    public void setTransportAccount(String transportAccount) {
        this.transportAccount = transportAccount;
    }

    public String getTransportCommonts() {
        return transportCommonts;
    }

    public void setTransportCommonts(String transportCommonts) {
        this.transportCommonts = transportCommonts;
    }

    public String getTransportImage() {
        return transportImage;
    }

    public void setTransportImage(String transportImage) {
        this.transportImage = transportImage;
    }

    public int getTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(int transportStatus) {
        this.transportStatus = transportStatus;
    }

    public String getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(String transportTime) {
        this.transportTime = transportTime;
    }
}
