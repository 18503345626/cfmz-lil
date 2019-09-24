package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {
    List<Album> selectAll(@Param("page")Integer page, @Param("rows")Integer rows);
    Integer selectCount();
    void insertOne(Album album);
    void updateOne(Album album);
    void deleteById(String id);
}
