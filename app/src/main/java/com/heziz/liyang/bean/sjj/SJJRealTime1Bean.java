package com.heziz.liyang.bean.sjj;

/**
 * Created by sws on 2019-09-17.
 * from:
 * describe:
 */

public class SJJRealTime1Bean {
    private String relationId;//设备编号

    private String dataType;//数据类型：12.人员身份验证信息

    private String dataTime;

    private String authenticationStateResult;//识别状态值

    private String name;//用户姓名

    private String userId;//用户id

    private String authenticationPer;//识别分数百分比

    private String IdCard;//身份证号

    private String crc;//CRC

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getAuthenticationStateResult() {
        return authenticationStateResult;
    }

    public void setAuthenticationStateResult(String authenticationStateResult) {
        this.authenticationStateResult = authenticationStateResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthenticationPer() {
        return authenticationPer;
    }

    public void setAuthenticationPer(String authenticationPer) {
        this.authenticationPer = authenticationPer;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }
}
