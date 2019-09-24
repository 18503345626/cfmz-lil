package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {
    List<Chapter> selectAll(@Param("page")Integer page , @Param("rows") Integer rows, @Param("albumId") String albumId);
    Integer selectCount(String albumId);
    void insertOne(Chapter chapter);
    void updateOne(Chapter chapter);
    void deleteById(String id);
}
