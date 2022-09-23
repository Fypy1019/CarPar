package com.qf.news.service;

import com.qf.entity.News;
import com.qf.news.dao.NewsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsDao newsDao;
    @Override
    public List<News> newsList(Integer index,String title) {
        return newsDao.newsList(index,title);
    }

    @Override
    public News findNewsById(Integer id) {
        return newsDao.findNewsById(id);
    }

    @Override
    public Integer deleteNewsById(Integer id) {
        return newsDao.deleteNewsById(id);
    }

    @Override
    public List<News> findNewsByTitle(News news) {
        return newsDao.findNewsByTitle(news);
    }

    @Override
    public Integer updateNews(News news) {
        return newsDao.updateNews(news);
    }

    @Override
    public Integer addNews(News news) {
        return newsDao.addNews(news);
    }

    @Override
    public Integer newsCount(String title) {
        return newsDao.newsCount(title);
    }
}
