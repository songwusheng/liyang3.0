package com.heziz.liyang.bean.rcjc;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-27.
 * from:
 * describe:
 */

public class RcjcBean implements Serializable {

    /**
     * total : 223
     * finished : 0
     * unFinished : 223
     * passRate : 0
     * pass : 0
     * unPass : 223
     */

    private int total;
    private int finished;
    private int unFinished;
    private double passRate;
    private int pass;
    private int unPass;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getUnFinished() {
        return unFinished;
    }

    public void setUnFinished(int unFinished) {
        this.unFinished = unFinished;
    }

    public double getPassRate() {
        return passRate;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getUnPass() {
        return unPass;
    }

    public void setUnPass(int unPass) {
        this.unPass = unPass;
    }

    @Override
    public String toString() {
        return "RcjcBean{" +
                "total=" + total +
                ", finished=" + finished +
                ", unFinished=" + unFinished +
                ", passRate=" + passRate +
                ", pass=" + pass +
                ", unPass=" + unPass +
                '}';
    }
}
