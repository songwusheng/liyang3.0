package com.heziz.liyang.network;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class PageBean implements Serializable {

    /**
     * pageNow : 1
     * pageSize : 10
     * totalCount : 1
     * totalPageCount : 1
     * startPos : 0
     */

    private int pageNow;
    private int pageSize;
    private int totalCount;
    private int totalPageCount;
    private int startPos;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }
}
