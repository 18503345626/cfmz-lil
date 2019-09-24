package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {
    List<Banner> selectAll(@Param("page") Integer page , @Param("rows") Integer rows);
    Integer selectCount();
    void insertOne(Banner banner);
    void updateOne(Banner banner);
    void deleteById(String id);

    Banner selectById(String rowId);

    List<Banner> selectAllBanner();//不分页查询所有banner
}
