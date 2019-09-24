package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumDAO albumDAO;
    @Resource
    private ChapterDAO chapterDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String,Object> showAll(Integer page, Integer rows) {
        List<Album> albums = albumDAO.selectAll(page, rows);
        Map<String, Object>  map = new HashMap<>();
        map.put("rows",albums);
        Integer count = albumDAO.selectCount();
        map.put("records",count);
        Integer total= count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public String addOne(Album album) {
       String id =UUID.randomUUID().toString();
        album.setId(id);
        album.setCrea_date(new Date());
        albumDAO.insertOne(album);
        return id;
    }

    @Override
    public String updateOne(Album album) {
        albumDAO.updateOne(album);
        if(album.getCover().equals("")||album.getCover()==null){
            return null;
        }else {
            return album.getId();
        }
    }

    @Override
    public void deleteOne(String id) {
        Integer count = chapterDAO.selectCount(id);
        if(count>0){
            System.out.println("删除失败");
        }else{
            albumDAO.deleteById(id);
        }
    }
}
