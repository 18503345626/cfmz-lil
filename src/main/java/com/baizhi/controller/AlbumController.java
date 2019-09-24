package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;


    //分页查询
    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page, Integer rows)throws Exception{
        Map<String, Object> map = albumService.showAll(page, rows);
        return map;
    }

    //增删改操作
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Album album, String oper)throws Exception{
        String id=null;
        if(oper.equals("add")){
            id=albumService.addOne(album);
        }
        if(oper.equals("edit")){
            id = albumService.updateOne(album);
        }
        if(oper.equals("del")){
            albumService.deleteOne(album.getId());
        }
        return id;
    }
    //专辑封面查询
    @RequestMapping("albumUpload")
    public void albumUpload(MultipartFile cover, String id, HttpServletRequest request)throws Exception{
        String realPath = request.getServletContext().getRealPath("/upload/photo");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs(); //创建文件夹
        }
        String filename = cover.getOriginalFilename();
        //给文件加一个时间戳
        String name=new Date().getTime()+"-"+filename;
        cover.transferTo(new File(realPath,name));
        //执行修改  修改文件的路径
        Album album= new Album();
        album.setId(id);
        album.setCover(name);
        albumService.updateOne(album);
    }
}
