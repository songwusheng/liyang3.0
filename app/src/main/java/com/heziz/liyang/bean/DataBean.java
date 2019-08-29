package com.heziz.liyang.bean;

/**
 * Created by sws on 2019-08-20.
 * from:
 * describe:
 */

public class DataBean {

    /**
     * id : 1566277330206680
     * citynameId : 1347
     * result : {"future":{"day_20190826":{"date":"20190826","weather_id":{"fa":"01","fb":"00"},"week":"星期一","temperature":"24℃~34℃","weather":"多云转晴","wind":"东风微风"},"day_20190825":{"date":"20190825","weather_id":{"fa":"01","fb":"00"},"week":"星期日","temperature":"24℃~34℃","weather":"多云转晴","wind":"东风微风"},"day_20190822":{"date":"20190822","weather_id":{"fa":"01","fb":"01"},"week":"星期四","temperature":"24℃~33℃","weather":"多云","wind":"东北风微风"},"day_20190821":{"date":"20190821","weather_id":{"fa":"01","fb":"00"},"week":"星期三","temperature":"24℃~34℃","weather":"多云转晴","wind":"东风微风"},"day_20190824":{"date":"20190824","weather_id":{"fa":"01","fb":"07"},"week":"星期六","temperature":"24℃~32℃","weather":"多云转小雨","wind":"东北风微风"},"day_20190823":{"date":"20190823","weather_id":{"fa":"01","fb":"01"},"week":"星期五","temperature":"24℃~32℃","weather":"多云","wind":"东风微风"},"day_20190820":{"date":"20190820","weather_id":{"fa":"01","fb":"00"},"week":"星期二","temperature":"24℃~32℃","weather":"多云转晴","wind":"东风微风"}},"today":{"weather_id":{"fa":"01","fb":"00"},"week":"星期二","city":"浦口","dressing_index":"炎热","travel_index":"适宜","wash_index":"较适宜","comfort_index":"","exercise_index":"适宜","dressing_advice":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","uv_index":"中等","drying_index":"","temperature":"24℃~32℃","weather":"多云转晴","date_y":"2019年08月20日","wind":"东风微风"},"sk":{"temp":"30","humidity":"60%","wind_direction":"东风","time":"12:52","wind_strength":"2级"}}
     * datetime : 1566277330206
     */

    private long id;
    private String citynameId;
    private String result;
    private long datetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCitynameId() {
        return citynameId;
    }

    public void setCitynameId(String citynameId) {
        this.citynameId = citynameId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
