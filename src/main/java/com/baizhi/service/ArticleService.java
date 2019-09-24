package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.HashMap;
import java.util.Map;

public interface ArticleService {
    Map<String,Object> showAll(Integer page,Integer rows);
    void deleteOne(String id);
    //异步修改
    HashMap<String, Object> updateArticle(Article article);
    //添加文章
    HashMap<String, Object> addArticle(Article article);
}
