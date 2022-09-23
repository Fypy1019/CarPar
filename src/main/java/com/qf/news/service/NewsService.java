package com.qf.news.service;

import com.qf.entity.News;

import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
public interface NewsService {
    List<News> newsList(Integer index,String title);

    News findNewsById(Integer id);

    Integer deleteNewsById(Integer id);

    List<News> findNewsByTitle(News news);

    Integer updateNews(News news);

    Integer addNews(News news);

    Integer newsCount(String title);
}
