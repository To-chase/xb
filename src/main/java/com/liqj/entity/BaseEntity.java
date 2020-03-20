package com.liqj.entity;

import java.io.Serializable;

/**
 * @author Liqj
 * @date 2020/3/17 16:27
 */
public class BaseEntity implements Serializable {
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private Integer createBy;
    /**
     * 删除标记
     */
    private Integer delFlag;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
