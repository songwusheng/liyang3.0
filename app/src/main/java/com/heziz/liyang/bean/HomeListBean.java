package com.heziz.liyang.bean;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-25.
 * from:
 * describe:
 */

public class HomeListBean<T> implements Serializable {

    /**
     * popedom : 1552292505892246
     * popedomName : 栖霞
     * listSize : 47
     * diff : 1
     * build : 46
     * fj : 40
     * sz : 1
     * yl : 0
     * jt : 0
     * sl : 0
     * dt : 0
     * qt : 0
     */

    private String popedom;
    private String popedomName;
    private int listSize;
    private int diff;
    private int build;
    private int fj;
    private int sz;
    private int yl;
    private int jt;
    private int sl;
    private int dt;
    private int qt;
    private double cost;
    private int szg;
    private int qzg;
    private T list;

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getSzg() {
        return szg;
    }

    public void setSzg(int szg) {
        this.szg = szg;
    }

    public int getQzg() {
        return qzg;
    }

    public void setQzg(int qzg) {
        this.qzg = qzg;
    }

    public String getPopedom() {
        return popedom;
    }

    public void setPopedom(String popedom) {
        this.popedom = popedom;
    }

    public String getPopedomName() {
        return popedomName;
    }

    public void setPopedomName(String popedomName) {
        this.popedomName = popedomName;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    public int getFj() {
        return fj;
    }

    public void setFj(int fj) {
        this.fj = fj;
    }

    public int getSz() {
        return sz;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public int getYl() {
        return yl;
    }

    public void setYl(int yl) {
        this.yl = yl;
    }

    public int getJt() {
        return jt;
    }

    public void setJt(int jt) {
        this.jt = jt;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }
}
