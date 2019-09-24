package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //分页查询
    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows)throws Exception{
        Map<String, Object> map = articleService.showAll(page, rows);
        return map;
    }

    //文章的增删改操作
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Album album, String oper)throws Exception{
        String id=null;
        if(oper.equals("add")){

        }
        if(oper.equals("edit")){

        }
        if(oper.equals("del")){
            articleService.deleteOne(album.getId());
        }
        return id;
    }


    //修改文章
    @RequestMapping("/updateArticle")
    @ResponseBody
    public HashMap<String,Object> updateArticle(Article article){
       HashMap<String,Object> map= articleService.updateArticle(article);
       return map;
    }

    //添加文章
    @RequestMapping("/addArticle")
    @ResponseBody
    public HashMap<String,Object> addArticle(Article article){
        HashMap<String,Object> map= articleService.addArticle(article);
        return map;
    }
}
