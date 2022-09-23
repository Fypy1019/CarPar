package com.qf.news.dao;

import com.qf.entity.News;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
@Mapper
public interface NewsDao {

    @SelectProvider(type = NewsProvider.class, method = "newsList")
    List<News> newsList(@Param("index") Integer index,@Param("title") String title);

    @Select("SELECT * FROM news WHERE news_id=#{id}")
    News findNewsById( @Param("id") Integer id);

    @Delete("DELETE FROM news WHERE news_id=#{id}")
    Integer deleteNewsById(@Param("id")Integer id);

    @SelectProvider(type = NewsProvider.class, method = "findNewsByTitle")
    List<News> findNewsByTitle(@Param("news")News news);

    @UpdateProvider(type = NewsProvider.class, method = "updateNews")
    Integer updateNews(@Param("news") News news);

    @Insert("INSERT INTO news (news_title,news_content)" +
            "VALUES(#{news.newsTitle},#{news.newsContent})")
    Integer addNews(@Param("news") News news);

    @Select("SELECT COUNT(*) FROM news WHERE news_title LIKE '%${title}%'")
    Integer newsCount(@Param("title") String title);
}
