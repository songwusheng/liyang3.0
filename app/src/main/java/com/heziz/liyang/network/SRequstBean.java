package com.heziz.liyang.network;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-25.
 * from:
 * describe:
 */

public class SRequstBean<T> implements Serializable {

    /**
     * optCode : QUERY_PROJECTINFO
     * optDesc : 查询项目
     * resultCode : 000000
     * resultDesc : 操作成功
     * data : T
     * module : HZXM
     * success : true
     * bizCode : HZXM-QUERY_PROJECTINFO-000000
     */

    private String optCode;
    private String optDesc;
    private String resultCode;
    private String resultDesc;
    private T data;
    private String module;
    private boolean success;
    private String bizCode;

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public String getOptDesc() {
        return optDesc;
    }

    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
}
