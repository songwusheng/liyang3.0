package com.heziz.liyang.bean.rcjc;

/**
 * Created by sws on 2019-12-02.
 * from:监管方--日常检查详情界面--整改结果修改
 * describe:
 */

public class ChangeStatusBean {

    /**
     * endStatus : 6
     * endTime : 2019-11-27 15:13:38
     * id : 1206
     * enclosureStatus : 1
     * hardeningStatus : 1
     * coverageStatus : 1
     * cleaningStatus : 1
     * inoutStatus : 1
     * publicityStatus : 1
     * oilsStatus : 1
     * engineStatus : 1
     * transportStatus : 1
     * remarks : XX
     */

    private String endStatus;
    private String endTime;
    private int id;
    private String enclosureStatus;
    private String hardeningStatus;
    private String coverageStatus;
    private String cleaningStatus;
    private String inoutStatus;
    private String publicityStatus;
    private String oilsStatus;
    private String engineStatus;
    private String transportStatus;
    private String remarks;

    public ChangeStatusBean() {
    }

    public ChangeStatusBean(String endStatus, String endTime, int id, String enclosureStatus, String hardeningStatus, String coverageStatus, String cleaningStatus, String inoutStatus, String publicityStatus, String oilsStatus, String engineStatus, String transportStatus, String remarks) {
        this.endStatus = endStatus;
        this.endTime = endTime;
        this.id = id;
        this.enclosureStatus = enclosureStatus;
        this.hardeningStatus = hardeningStatus;
        this.coverageStatus = coverageStatus;
        this.cleaningStatus = cleaningStatus;
        this.inoutStatus = inoutStatus;
        this.publicityStatus = publicityStatus;
        this.oilsStatus = oilsStatus;
        this.engineStatus = engineStatus;
        this.transportStatus = transportStatus;
        this.remarks = remarks;
    }

    public String getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(String endStatus) {
        this.endStatus = endStatus;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnclosureStatus() {
        return enclosureStatus;
    }

    public void setEnclosureStatus(String enclosureStatus) {
        this.enclosureStatus = enclosureStatus;
    }

    public String getHardeningStatus() {
        return hardeningStatus;
    }

    public void setHardeningStatus(String hardeningStatus) {
        this.hardeningStatus = hardeningStatus;
    }

    public String getCoverageStatus() {
        return coverageStatus;
    }

    public void setCoverageStatus(String coverageStatus) {
        this.coverageStatus = coverageStatus;
    }

    public String getCleaningStatus() {
        return cleaningStatus;
    }

    public void setCleaningStatus(String cleaningStatus) {
        this.cleaningStatus = cleaningStatus;
    }

    public String getInoutStatus() {
        return inoutStatus;
    }

    public void setInoutStatus(String inoutStatus) {
        this.inoutStatus = inoutStatus;
    }

    public String getPublicityStatus() {
        return publicityStatus;
    }

    public void setPublicityStatus(String publicityStatus) {
        this.publicityStatus = publicityStatus;
    }

    public String getOilsStatus() {
        return oilsStatus;
    }

    public void setOilsStatus(String oilsStatus) {
        this.oilsStatus = oilsStatus;
    }

    public String getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(String engineStatus) {
        this.engineStatus = engineStatus;
    }

    public String getTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(String transportStatus) {
        this.transportStatus = transportStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
