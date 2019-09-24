package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {

    Map<String,Object> showAll(Integer page ,Integer rows ,String albumId);

    String addOne(Chapter chapter);

    String updateOne(Chapter chapter);

    void deleteOne(String id);
}
