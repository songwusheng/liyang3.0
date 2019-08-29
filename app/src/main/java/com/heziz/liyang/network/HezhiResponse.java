package com.heziz.liyang.network;

import java.io.Serializable;

/**
 * Created by hasee on 2017/home_bg_icon3/20.
 */

public class HezhiResponse<T> implements Serializable {
    public String errorCode ;
    public String errorMsg;
    public String successFlg;

    public T data;

    @Override
    public String toString() {
        return "HezhiResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", successFlg='" + successFlg + '\'' +
                ", data=" + data +
                '}';
    }

}
