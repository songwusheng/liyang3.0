package com.heziz.liyang.bean.rcjc;

/**
 * Created by sws on 2019-12-25.
 * from:
 * describe:
 */

public class PushPlusBean {

    /**
     * account : 待推送的账户
     * context : 推送的文本
     * subtitle : 推送文本的缩写
     * title : 推送的标题
     * zg : 1
     * zgdsh : 1
     * zxdfc : 1
     * zxdzg : 1
     */

    private String account;
    private String context;
    private String subtitle;
    private String title;
    private int zg;
    private int zgdsh;
    private int zxdfc;
    private int zxdzg;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getZg() {
        return zg;
    }

    public void setZg(int zg) {
        this.zg = zg;
    }

    public int getZgdsh() {
        return zgdsh;
    }

    public void setZgdsh(int zgdsh) {
        this.zgdsh = zgdsh;
    }

    public int getZxdfc() {
        return zxdfc;
    }

    public void setZxdfc(int zxdfc) {
        this.zxdfc = zxdfc;
    }

    public int getZxdzg() {
        return zxdzg;
    }

    public void setZxdzg(int zxdzg) {
        this.zxdzg = zxdzg;
    }
}
