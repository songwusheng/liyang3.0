package com.heziz.liyang.bean.car;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-06.
 * from:
 * describe:
 */

public class CarWZBean implements Serializable {

    /**
     * Today : 0
     * ThisWeek : 0
     * ThisMonth : 0
     */

    private String Today;
    private String ThisWeek;
    private String ThisMonth;

    public String getToday() {
        return Today;
    }

    public void setToday(String Today) {
        this.Today = Today;
    }

    public String getThisWeek() {
        return ThisWeek;
    }

    public void setThisWeek(String ThisWeek) {
        this.ThisWeek = ThisWeek;
    }

    public String getThisMonth() {
        return ThisMonth;
    }

    public void setThisMonth(String ThisMonth) {
        this.ThisMonth = ThisMonth;
    }
}
