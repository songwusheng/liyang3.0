package com.heziz.liyang.bean.yc;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class YcRealTimeBean implements Serializable {

    /**
     * DI1-Rtd : 0
     * && : 74C0
     * DI1-Flag : N
     * logstashInfoName : weatherStation
     * LA-Flag : N
     * PW : 123456
     * a34002-Flag : N
     * DataTime : 20190501074902
     * a01008-Rtd : 72
     * type : yangchen
     * a01006-Flag : N
     * noisestate : 0
     * sysdate : 2019-05-01 07:48:59
     * @version : 1
     * a01001-Rtd : 18.7
     * state : 0
     * a01007-Flag : N
     * QN : 20190501074902000
     * a34004-Rtd : 98600
     * pm10state : 1
     * ## : 0389
     * MN : 20190328000005
     * ST : 22
     * projectname : null
     * a34001-Flag : N
     * a01001-Flag : N
     * a01002-Rtd : 74
     * CN : 2011
     * a34001-Rtd : 169.5
     * a34004-Flag : N
     * Flag : 4
     * a01006-Rtd : 101.324
     * LA-Rtd : 51.1
     * @timestamp : 2019-04-30T23:49:18.793Z
     * noiseflag : false
     * pm10flag : true
     * a01008-Flag : N
     * a01007-Rtd : 0.5
     * a34002-Rtd : 152
     * a01002-Flag : N
     */

    @SerializedName("DI1-Rtd")
    private int DI1Rtd;
    @SerializedName("&&")
    private String _$9; // FIXME check this code
    @SerializedName("DI1-Flag")
    private String DI1Flag;
    private String logstashInfoName;
    @SerializedName("LA-Flag")
    private String LAFlag;
    private String PW;
    @SerializedName("a34002-Flag")
    private String a34002Flag;
    private long DataTime;
    @SerializedName("a01008-Rtd")
    private double a01008Rtd;
    private String type;
    @SerializedName("a01006-Flag")
    private String a01006Flag;
    private String noisestate;
    private String sysdate;
    @SerializedName("@version")
    private String _$Version116; // FIXME check this code
    @SerializedName("a01001-Rtd")
    private double a01001Rtd;
    private String state;
    @SerializedName("a01007-Flag")
    private String a01007Flag;
    private String QN;
    @SerializedName("a34004-Rtd")
    private double a34004Rtd;
    private String pm10state;
    @SerializedName("##")
    private String _$304; // FIXME check this code
    private String MN;
    private String ST;
    private Object projectname;
    @SerializedName("a34001-Flag")
    private String a34001Flag;
    @SerializedName("a01001-Flag")
    private String a01001Flag;
    @SerializedName("a01002-Rtd")
    private double a01002Rtd;
    private String CN;
    @SerializedName("a34001-Rtd")
    private double a34001Rtd;
    @SerializedName("a34004-Flag")
    private String a34004Flag;
    private String Flag;
    @SerializedName("a01006-Rtd")
    private double a01006Rtd;
    @SerializedName("LA-Rtd")
    private double LARtd;
    @SerializedName("@timestamp")
    private String _$Timestamp77; // FIXME check this code
    private String noiseflag;
    private String pm10flag;
    @SerializedName("a01008-Flag")
    private String a01008Flag;
    @SerializedName("a01007-Rtd")
    private double a01007Rtd;
    @SerializedName("a34002-Rtd")
    private double a34002Rtd;
    @SerializedName("a01002-Flag")
    private String a01002Flag;

    public int getDI1Rtd() {
        return DI1Rtd;
    }

    public void setDI1Rtd(int DI1Rtd) {
        this.DI1Rtd = DI1Rtd;
    }

    public String get_$9() {
        return _$9;
    }

    public void set_$9(String _$9) {
        this._$9 = _$9;
    }

    public String getDI1Flag() {
        return DI1Flag;
    }

    public void setDI1Flag(String DI1Flag) {
        this.DI1Flag = DI1Flag;
    }

    public String getLogstashInfoName() {
        return logstashInfoName;
    }

    public void setLogstashInfoName(String logstashInfoName) {
        this.logstashInfoName = logstashInfoName;
    }

    public String getLAFlag() {
        return LAFlag;
    }

    public void setLAFlag(String LAFlag) {
        this.LAFlag = LAFlag;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public String getA34002Flag() {
        return a34002Flag;
    }

    public void setA34002Flag(String a34002Flag) {
        this.a34002Flag = a34002Flag;
    }

    public long getDataTime() {
        return DataTime;
    }

    public void setDataTime(long dataTime) {
        DataTime = dataTime;
    }

    public double getA01008Rtd() {
        return a01008Rtd;
    }

    public void setA01008Rtd(double a01008Rtd) {
        this.a01008Rtd = a01008Rtd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getA01006Flag() {
        return a01006Flag;
    }

    public void setA01006Flag(String a01006Flag) {
        this.a01006Flag = a01006Flag;
    }

    public String getNoisestate() {
        return noisestate;
    }

    public void setNoisestate(String noisestate) {
        this.noisestate = noisestate;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String get_$Version116() {
        return _$Version116;
    }

    public void set_$Version116(String _$Version116) {
        this._$Version116 = _$Version116;
    }

    public double getA01001Rtd() {
        return a01001Rtd;
    }

    public void setA01001Rtd(double a01001Rtd) {
        this.a01001Rtd = a01001Rtd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getA01007Flag() {
        return a01007Flag;
    }

    public void setA01007Flag(String a01007Flag) {
        this.a01007Flag = a01007Flag;
    }

    public String getQN() {
        return QN;
    }

    public void setQN(String QN) {
        this.QN = QN;
    }

    public double getA34004Rtd() {
        return a34004Rtd;
    }

    public void setA34004Rtd(double a34004Rtd) {
        this.a34004Rtd = a34004Rtd;
    }

    public String getPm10state() {
        return pm10state;
    }

    public void setPm10state(String pm10state) {
        this.pm10state = pm10state;
    }

    public String get_$304() {
        return _$304;
    }

    public void set_$304(String _$304) {
        this._$304 = _$304;
    }

    public String getMN() {
        return MN;
    }

    public void setMN(String MN) {
        this.MN = MN;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public Object getProjectname() {
        return projectname;
    }

    public void setProjectname(Object projectname) {
        this.projectname = projectname;
    }

    public String getA34001Flag() {
        return a34001Flag;
    }

    public void setA34001Flag(String a34001Flag) {
        this.a34001Flag = a34001Flag;
    }

    public String getA01001Flag() {
        return a01001Flag;
    }

    public void setA01001Flag(String a01001Flag) {
        this.a01001Flag = a01001Flag;
    }

    public double getA01002Rtd() {
        return a01002Rtd;
    }

    public void setA01002Rtd(double a01002Rtd) {
        this.a01002Rtd = a01002Rtd;
    }

    public String getCN() {
        return CN;
    }

    public void setCN(String CN) {
        this.CN = CN;
    }

    public double getA34001Rtd() {
        return a34001Rtd;
    }

    public void setA34001Rtd(double a34001Rtd) {
        this.a34001Rtd = a34001Rtd;
    }

    public String getA34004Flag() {
        return a34004Flag;
    }

    public void setA34004Flag(String a34004Flag) {
        this.a34004Flag = a34004Flag;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public double getA01006Rtd() {
        return a01006Rtd;
    }

    public void setA01006Rtd(double a01006Rtd) {
        this.a01006Rtd = a01006Rtd;
    }

    public double getLARtd() {
        return LARtd;
    }

    public void setLARtd(double LARtd) {
        this.LARtd = LARtd;
    }

    public String get_$Timestamp77() {
        return _$Timestamp77;
    }

    public void set_$Timestamp77(String _$Timestamp77) {
        this._$Timestamp77 = _$Timestamp77;
    }

    public String getNoiseflag() {
        return noiseflag;
    }

    public void setNoiseflag(String noiseflag) {
        this.noiseflag = noiseflag;
    }

    public String getPm10flag() {
        return pm10flag;
    }

    public void setPm10flag(String pm10flag) {
        this.pm10flag = pm10flag;
    }

    public String getA01008Flag() {
        return a01008Flag;
    }

    public void setA01008Flag(String a01008Flag) {
        this.a01008Flag = a01008Flag;
    }

    public double getA01007Rtd() {
        return a01007Rtd;
    }

    public void setA01007Rtd(double a01007Rtd) {
        this.a01007Rtd = a01007Rtd;
    }

    public double getA34002Rtd() {
        return a34002Rtd;
    }

    public void setA34002Rtd(double a34002Rtd) {
        this.a34002Rtd = a34002Rtd;
    }

    public String getA01002Flag() {
        return a01002Flag;
    }

    public void setA01002Flag(String a01002Flag) {
        this.a01002Flag = a01002Flag;
    }
}
