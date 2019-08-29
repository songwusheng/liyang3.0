package com.heziz.liyang.bean.sp;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-22.
 * from:
 * describe:
 */

public class SpDevicezlxNumBean implements Serializable {

    /**
     * unknowdevicecount : 0
     * onlinedevice : 46
     * notonlinedevice : 45
     */

    private String unknowdevicecount;
    private String onlinedevice;
    private String notonlinedevice;

    public String getUnknowdevicecount() {
        return unknowdevicecount;
    }

    public void setUnknowdevicecount(String unknowdevicecount) {
        this.unknowdevicecount = unknowdevicecount;
    }

    public String getOnlinedevice() {
        return onlinedevice;
    }

    public void setOnlinedevice(String onlinedevice) {
        this.onlinedevice = onlinedevice;
    }

    public String getNotonlinedevice() {
        return notonlinedevice;
    }

    public void setNotonlinedevice(String notonlinedevice) {
        this.notonlinedevice = notonlinedevice;
    }
}
