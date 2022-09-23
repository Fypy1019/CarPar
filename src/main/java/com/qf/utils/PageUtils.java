package com.qf.utils;

public class PageUtils {
    final static Integer SIZE = 6;



    //设置分页信息
    public static Page settingPage(Integer index, Integer total){
        Page page = new Page();
        //当前起始下标
        page.setIndex(index);
        //总页数
        page.setTotalPage(totalPage(total));
        //上一页起始下标
        page.setBefore(page.getBefore());
        //下一页起始下标
        page.setAfter(page.getAfter());
        return page;
    }

    //计算总页数，可以整除取商，反之为商加 1
    public static Integer totalPage(Integer count){
        return count % SIZE == 0 ? count / SIZE : count / SIZE + 1;
    }
}
