package com.heziz.liyang.bean.wdbygs;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-28.
 * from:
 * describe:
 */

public class WDBYGSBean implements Serializable {

    /**
     * id : 1558516751975237
     * name : 666
     * xmLeader :
     * pCount : 0
     * wCount : 0
     * xjzt : null
     * jdName : 马群
     * wg : 韩冰
     */

    private long id;
    private String name;
    private String xmLeader;
    private int pCount;
    private int wCount;
    private Object xjzt;
    private String jdName;
    private String wg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXmLeader() {
        return xmLeader;
    }

    public void setXmLeader(String xmLeader) {
        this.xmLeader = xmLeader;
    }

    public int getPCount() {
        return pCount;
    }

    public void setPCount(int pCount) {
        this.pCount = pCount;
    }

    public int getWCount() {
        return wCount;
    }

    public void setWCount(int wCount) {
        this.wCount = wCount;
    }

    public Object getXjzt() {
        return xjzt;
    }

    public void setXjzt(Object xjzt) {
        this.xjzt = xjzt;
    }

    public String getJdName() {
        return jdName;
    }

    public void setJdName(String jdName) {
        this.jdName = jdName;
    }

    public String getWg() {
        return wg;
    }

    public void setWg(String wg) {
        this.wg = wg;
    }
}
