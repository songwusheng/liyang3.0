package com.heziz.liyang.bean;

/**
 * Created by sws on 2019-08-21.
 * from:
 * describe:
 */

public class KqzlBean {

    /**
     * id : 1566370918671354
     * type : 1
     * cityNamePinyin : nanjing
     * lasttime : 1566370918671
     * result : [{"lastMoniData":{"1":{"PM2.5Hour":"19","PM2.5Day":"19","PM10Hour":"47","city":"迈皋桥","America_AQI":"144","AQI":"99","lon":"118.803056","lat":"32.108333","quality":"良"},"2":{"PM2.5Hour":"32","PM2.5Day":"32","PM10Hour":"44","city":"草场门","America_AQI":"149","AQI":"102","lon":"118.747222","lat":"32.057222","quality":"轻度污染"},"3":{"PM2.5Hour":"30","PM2.5Day":"30","PM10Hour":"50","city":"山西路","America_AQI":"148","AQI":"101","lon":"118.773611","lat":"32.07","quality":"轻度污染"},"4":{"PM2.5Hour":"24","PM2.5Day":"24","PM10Hour":"44","city":"中华门","America_AQI":"121","AQI":"74","lon":"118.776944","lat":"32.014444","quality":"良"},"5":{"PM2.5Hour":"29","PM2.5Day":"29","PM10Hour":"43","city":"瑞金路","America_AQI":"132","AQI":"85","lon":"118.803056","lat":"32.031389","quality":"良"},"6":{"PM2.5Hour":"24","PM2.5Day":"24","PM10Hour":"50","city":"玄武湖","America_AQI":"123","AQI":"77","lon":"118.793611","lat":"32.0775","quality":"良"},"7":{"PM2.5Hour":"22","PM2.5Day":"22","PM10Hour":"56","city":"浦口","America_AQI":"175","AQI":"113","lon":"118.632034","lat":"32.086938","quality":"轻度污染"},"8":{"PM2.5Hour":"28","PM2.5Day":"28","PM10Hour":"59","city":"奥体中心","America_AQI":"133","AQI":"87","lon":"118.740185","lat":"32.008731","quality":"良"},"9":{"PM2.5Hour":"24","PM2.5Day":"24","PM10Hour":"56","city":"仙林大学城","America_AQI":"148","AQI":"101","lon":"118.910839","lat":"32.105843","quality":"轻度污染"}},"citynow":{"date":"2019-08-21 14:00","city":"南京","AQI":"95","quality":"良"},"lastTwoWeeks":{"22":{"date":"2019-08-14","city":"南京","AQI":"31","quality":"优"},"23":{"date":"2019-08-15","city":"南京","AQI":"42","quality":"优"},"24":{"date":"2019-08-16","city":"南京","AQI":"55","quality":"良"},"25":{"date":"2019-08-17","city":"南京","AQI":"50","quality":"优"},"26":{"date":"2019-08-18","city":"南京","AQI":"46","quality":"优"},"27":{"date":"2019-08-19","city":"南京","AQI":"39","quality":"优"},"28":{"date":"2019-08-20","city":"南京","AQI":"58","quality":"良"},"10":{"date":"2019-08-02","city":"南京","AQI":"25","quality":"优"},"11":{"date":"2019-08-03","city":"南京","AQI":"47","quality":"优"},"12":{"date":"2019-08-04","city":"南京","AQI":"24","quality":"优"},"13":{"date":"2019-08-05","city":"南京","AQI":"37","quality":"优"},"14":{"date":"2019-08-06","city":"南京","AQI":"37","quality":"优"},"15":{"date":"2019-08-07","city":"南京","AQI":"45","quality":"优"},"16":{"date":"2019-08-08","city":"南京","AQI":"31","quality":"优"},"17":{"date":"2019-08-09","city":"南京","AQI":"35","quality":"优"},"18":{"date":"2019-08-10","city":"南京","AQI":"10","quality":"优"},"19":{"date":"2019-08-11","city":"南京","AQI":"23","quality":"优"},"1":{"date":"2019-07-24","city":"南京","AQI":"47","quality":"优"},"2":{"date":"2019-07-25","city":"南京","AQI":"52","quality":"良"},"3":{"date":"2019-07-26","city":"南京","AQI":"42","quality":"优"},"4":{"date":"2019-07-27","city":"南京","AQI":"47","quality":"优"},"5":{"date":"2019-07-28","city":"南京","AQI":"47","quality":"优"},"6":{"date":"2019-07-29","city":"南京","AQI":"39","quality":"优"},"7":{"date":"2019-07-30","city":"南京","AQI":"45","quality":"优"},"8":{"date":"2019-07-31","city":"南京","AQI":"51","quality":"良"},"9":{"date":"2019-08-01","city":"南京","AQI":"43","quality":"优"},"20":{"date":"2019-08-12","city":"南京","AQI":"28","quality":"优"},"21":{"date":"2019-08-13","city":"南京","AQI":"34","quality":"优"}}}]
     */

    private long id;
    private String type;
    private String cityNamePinyin;
    private long lasttime;
    private String result;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCityNamePinyin() {
        return cityNamePinyin;
    }

    public void setCityNamePinyin(String cityNamePinyin) {
        this.cityNamePinyin = cityNamePinyin;
    }

    public long getLasttime() {
        return lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
