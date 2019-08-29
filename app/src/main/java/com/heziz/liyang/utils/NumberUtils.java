package com.heziz.liyang.utils;

import java.text.DecimalFormat;

/**
 * Created by sws on 2018/5/9.
 * from:
 * describe:
 */

public class NumberUtils {
    public static double getTwoDecimal(double num) {
        DecimalFormat dFormat=new DecimalFormat("0.00");
        String yearString=dFormat.format(num);
        Double temp= Double.valueOf(yearString);
        return temp;
    }
}
