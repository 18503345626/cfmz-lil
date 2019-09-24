package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import com.baizhi.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDAO articleDAO;
    @Override
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = articleDAO.selectAll(page, rows);
        map.put("rows",articles);
        Integer count = articleDAO.selectCount();
        map.put("records",count);
        Integer total= count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        map.put("page",page);
        return map;
    }
    //删除
    @Override
    public void deleteOne(String id) {
        articleDAO.deleteById(id);
    }

    //修改
    @Override
    public HashMap<String, Object> updateArticle(Article article) {
        HashMap<String, Object> map=new HashMap<>();
        try {
            articleDAO.updateOne(article);
            map.put("success",200);
            map.put("message","修改成功");
        }catch (Exception e){
            map.put("success",400);
            map.put("message","修改失败");
        }
        return map;
    }

    //添加
    @Override
    public HashMap<String, Object> addArticle(Article article) {
        HashMap<String, Object> map=new HashMap<>();
        try {
            article.setId(UUIDUtil.getUUID());
            article.setCrea_date(new Date());
            article.setGuru_id("1");
            articleDAO.insertOne(article);
            map.put("success",200);
            map.put("message","添加成功");
        }catch (Exception e){
            map.put("success",400);
            map.put("message","添加失败");
        }
        return map;
    }
}
