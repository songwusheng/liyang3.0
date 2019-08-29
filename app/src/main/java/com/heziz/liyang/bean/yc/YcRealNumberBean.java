package com.heziz.liyang.bean.yc;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-05.
 * from:
 * describe:
 */

public class YcRealNumberBean implements Serializable {

    /**
     * timeSlot : 2019-05-05T16:00:00&2019-05-05T16:54:50
     * avg : 51.50
     */

    private String timeSlot;
    private String avg;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }
}
