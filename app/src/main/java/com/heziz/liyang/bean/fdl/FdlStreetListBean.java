package com.heziz.liyang.bean.fdl;

import java.io.Serializable;

/**
 * Created by sws on 2019-05-17.
 * from:
 * describe:
 */

public class FdlStreetListBean  implements Serializable{

    /**
     * totalRecord : 0
     * qualifiedNumber : 0
     * unqualifiedNumber : 0
     * totalAmount : null
     * id : 1552292505892246
     * name : 栖霞
     */
    private int totalRecord;
    private int qualifiedNumber;
    private int unqualifiedNumber;
    private Object totalAmount;
    private long id;
    private String name;

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getQualifiedNumber() {
        return qualifiedNumber;
    }

    public void setQualifiedNumber(int qualifiedNumber) {
        this.qualifiedNumber = qualifiedNumber;
    }

    public int getUnqualifiedNumber() {
        return unqualifiedNumber;
    }

    public void setUnqualifiedNumber(int unqualifiedNumber) {
        this.unqualifiedNumber = unqualifiedNumber;
    }

    public Object getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Object totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
