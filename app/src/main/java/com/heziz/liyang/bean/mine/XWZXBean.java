package com.heziz.liyang.bean.mine;

import java.io.Serializable;

/**
 * Created by apple on 2019/6/28.
 */

public class XWZXBean implements Serializable {

    /**
     * id : 12
     * createBy : 1
     * createTime : 2019-06-21 09:13:08
     * updateBy : null
     * updateTime : 2019-06-21 09:13:08
     * fileExtension : pdf
     * originalName : 150
     * uploadName : 120190621091307.pdf
     * uploadPath : https://hzi-dev.oss-cn-beijing.aliyuncs.com/datas/image/2019/06/21/120190621091307.pdf
     */

    private int id;
    private Long createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private String fileExtension;
    private String originalName;
    private String uploadName;
    private String uploadPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
