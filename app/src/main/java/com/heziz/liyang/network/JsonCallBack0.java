package com.heziz.liyang.network;

import com.lzy.okgo.callback.AbsCallback;

import okhttp3.Response;

/**
 * Created by hasee on 2017/home_bg_icon3/20.
 */
@SuppressWarnings("unchecked")
public abstract class JsonCallBack0<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(Response response) throws Throwable {
        String strByJson=response.body().string();

        return (T)strByJson;

    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
    }
}
