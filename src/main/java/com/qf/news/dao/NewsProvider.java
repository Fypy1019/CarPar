package com.qf.news.dao;

import com.qf.entity.News;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 15483 on 2021/4/18.
 */
public class NewsProvider {
    public String newsList(@Param("index") Integer index,@Param("title") String title){
        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("news");
                if (title != null && !"".equals((title))) {
                    this.WHERE("news_title LIKE '%${title}%'");
                }
                this.LIMIT("#{index},6");
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }


    public String findNewsByTitle(@Param("news") News news) {
        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("news");
                if (news.getNewsTitle() != null && !"".equals((news.getNewsTitle()))) {
                    this.WHERE("news_title LIKE '%${news.newsTitle}%'");
                }

            }
        }.toString();
        System.out.println(sql);
        return sql;
    }
    public String updateNews(@Param("news") final News news) {
        String sql = new SQL() {
            {
                this.UPDATE("news");
                if (news.getNewsTitle() != null && !"".equals(news.getNewsTitle())) {
                    this.SET("news_title = #{news.newsTitle}");
                }
                if (news.getNewsContent() != null&& !"".equals(news.getNewsContent())) {
                    this.SET("news_content = #{news.newsContent}");
                }
                this.WHERE("news_id=#{news.newsId}");
            }
        }.toString();
        return sql;
    }
}
