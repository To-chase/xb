package com.liqj.entity;

/**
 * @author Liqj
 * @date 2020/3/18 22:00
 */
public class Page {
    //每一页展示的数据数
    private Integer pageSize=5;
    //当前页数
    private Integer pageCurrent;
    //总记录数
    private Integer count;
    //总页数
    private Integer pageTotal;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageTotal() {
        pageTotal=this.count%this.pageSize==0?this.count/this.pageSize:this.count/this.pageSize+1;
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
