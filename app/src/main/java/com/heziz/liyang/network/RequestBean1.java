package com.heziz.liyang.network;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class RequestBean1<T> implements Serializable {

    private int totalCount;
    private T vos;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public T getVos() {
        return vos;
    }

    public void setVos(T vos) {
        this.vos = vos;
    }
}
