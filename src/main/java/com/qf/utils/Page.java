package com.qf.utils;

import java.io.Serializable;


//分页
public class Page {

    private Integer index = 0;
    private Integer before;
    private Integer after;
    private Integer totalPage;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    //上一页
    public Integer getBefore() {
        if (index == 0) {
            return -1;
        } else {
            return index - 6;
        }
    }

    public void setBefore(Integer before) {
        this.before = before;
    }

    //下一页
    public Integer getAfter() {
        if (index == (totalPage - 1) * 6) {
            return -1;
        } else {
            return index + 6;
        }
    }

    public void setAfter(Integer after) {
        this.after = after;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "index=" + index +
                ", before=" + before +
                ", after=" + after +
                ", totalPage=" + totalPage +
                '}';
    }
}
