package com.heziz.liyang.bean.car;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-28.
 * from:
 * describe:
 */

public class ClcxZlxBean implements Serializable{

    /**
     * notonlinecount : 11
     * onlinecount : 38
     * unknowcount : 3
     */

    private String notonlinecount;
    private String onlinecount;
    private String unknowcount;

    public String getNotonlinecount() {
        return notonlinecount;
    }

    public void setNotonlinecount(String notonlinecount) {
        this.notonlinecount = notonlinecount;
    }

    public String getOnlinecount() {
        return onlinecount;
    }

    public void setOnlinecount(String onlinecount) {
        this.onlinecount = onlinecount;
    }

    public String getUnknowcount() {
        return unknowcount;
    }

    public void setUnknowcount(String unknowcount) {
        this.unknowcount = unknowcount;
    }
}
