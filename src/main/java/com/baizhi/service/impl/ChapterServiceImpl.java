package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterDAO chapterDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> showAll(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = new HashMap<>();
        List<Chapter> chapters = chapterDAO.selectAll(page, rows, albumId);
        map.put("rows",chapters);
        map.put("page",page);
        Integer count = chapterDAO.selectCount(albumId);
        map.put("records",count);
        Integer total= count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        return map;
    }

    @Override
    public String addOne(Chapter chapter) {
        String id=UUID.randomUUID().toString();
        chapter.setId(id);
        chapter.setUp_date(new Date());
        chapterDAO.insertOne(chapter);
        return id;
    }

    @Override
    public String updateOne(Chapter chapter) {
        chapterDAO.updateOne(chapter);
        if(chapter.getUrl().equals("")||chapter.getUrl()==null){
            return "123";
        }else {
            return chapter.getId();
        }
    }

    @Override
    public void deleteOne(String id) {
        chapterDAO.deleteById(id);
    }
}
