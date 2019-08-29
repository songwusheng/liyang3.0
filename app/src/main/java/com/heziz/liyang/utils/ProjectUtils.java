package com.heziz.liyang.utils;

import static com.dahuatech.utilslib.ActivityUtils.startActivity;

/**
 * Created by sws on 2019-04-29.
 * from:
 * describe:
 */

public class ProjectUtils {
    public static String getType(String type){
        String s="";
        if(StringUtil.isNull(type)){
            s="-";
        }else if(type.equals("1")) {
            s = "房建";
        }else if (type.equals("2")) {
            s = "市政";
        }else if (type.equals("3")) {
            s = "交通";
        }else if (type.equals("4")) {
            s = "轨道";
        }else if (type.equals("5")) {
            s = "水务";
        }else if (type.equals("6")) {
            s = "园林";
        }else{
            s="其他";
        }
        return s;
    }

    public static String getGXJB(String gxjb){
        String s="";
        if(StringUtil.isNull(gxjb)){
            s="-";
        }else if(gxjb.equals("1")) {
            s = "市直管";
        }else if (gxjb.equals("2")) {
            s = "区直管";
        }
        return s;
    }
    public static String getDiff(String diff){
        String s="";
        if(StringUtil.isNull(diff)){
            s="-";
        }else if(diff.equals("1")) {
            s = "信息化工地";
        }else if (diff.equals("2")) {
            s = "智慧工地";
        }
        return s;
    }
    public static String getValue(String diff){
        String s="";
        if(StringUtil.isNull(diff)||"null".equals(diff)){
            s="-";
        }else{
             s = diff;
        }
        return s;
    }


}
