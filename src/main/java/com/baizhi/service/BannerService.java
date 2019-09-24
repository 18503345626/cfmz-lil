package com.baizhi.service;


import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    Map<String,Object> showAll(Integer page , Integer rows);
    String addOne(Banner banner);
    String updateOne(Banner banner);
    void deleteOne(String id);
    //修改状态
    void changeStatus(String rowId);

    List<Banner> showBanners();//返回所有能展示的轮播图
}
