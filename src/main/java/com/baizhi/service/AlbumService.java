package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    Map<String,Object> showAll(Integer page , Integer rows);

    String addOne(Album album);

    String updateOne(Album album);

    void deleteOne(String id);
}
