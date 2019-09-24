package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDAO {
    List<Article> selectAll(@Param("page")Integer page, @Param("rows")Integer rows);
    Integer selectCount();
    void insertOne(Article article);
    void updateOne(Article article);
    void deleteById(String id);
}
