package com.qf.news.controller;

import com.qf.entity.News;
import com.qf.news.service.NewsService;
import com.qf.utils.Page;
import com.qf.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    @GetMapping("/list")
    public String newsList(Model model,@RequestParam(defaultValue = "0") Integer index,String title){
        Integer count = newsService.newsCount(title);
        Page page = PageUtils.settingPage(index, count);
        List<News> newss = newsService.newsList(index,title);
        model.addAttribute("page",page);
        model.addAttribute("newss",newss);
        model.addAttribute("title",title);
        return "news/newsList.html";
    }
    @GetMapping("/findNewsById")
    public String findNewsById(Integer id,Integer flag,Model model){
        News news = newsService.findNewsById(id);
        model.addAttribute("news",news);
        if (flag==1){
            return "news/newsView.html";
        }else {
            return "news/newsUpdate.html";
        }
    }

    @GetMapping("/deleteNewsById")
    public String deleteNewsById(Integer id,Model model){
        Integer row = newsService.deleteNewsById(id);
        return "redirect:/news/list";
    }

    @GetMapping("/findNewsByTitle")
    public String findNewsByTitle(News news,Model model){
        List<News> newss = newsService.findNewsByTitle(news);
        model.addAttribute("newss",newss);
        return "news/newsList.html";
    }

    @PostMapping("/updateNews")
    public String updateNews(News news){
        Integer row = newsService.updateNews(news);
        return "redirect:/news/findNewsById?id="+news.getNewsId()+"&flag="+2;
    }

    @PostMapping("/addNews")
    public String addNews(News news){
        Integer row = newsService.addNews(news);
        return "redirect:/news/list";
    }
}
