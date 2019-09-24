package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private BannerService bannerService;
//    分页查询
    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows)throws Exception{
        Map<String, Object> map = bannerService.showAll(page, rows);
        return map;
    }
//    轮播图的增删改
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Banner banner, String oper)throws Exception{
        String id=null;
        if(oper.equals("add")){
            id=bannerService.addOne(banner);
        }
        if(oper.equals("edit")){
            id = bannerService.updateOne(banner);
        }
        if(oper.equals("del")){
            bannerService.deleteOne(banner.getId());
        }
        return id;
    }

    //轮播图上传
    @RequestMapping("bannerUpload")
    public void bannerUpload(MultipartFile img_path, String id, HttpServletRequest request)throws Exception{
        String realPath = request.getServletContext().getRealPath("/upload/photo");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs(); //创建文件夹
        }
        String filename = img_path.getOriginalFilename();
        //给文件加一个时间戳
        String name=new Date().getTime()+"-"+filename;
        img_path.transferTo(new File(realPath,name));
        //执行修改  修改文件的路径
        Banner banner = new Banner();
        banner.setId(id);
        banner.setImg_path(name);
        bannerService.updateOne(banner);
    }
    //修改轮播图状态
    @RequestMapping("changeStatus")
    @ResponseBody
    public void changeStatus(String rowId)throws Exception{
        bannerService.changeStatus(rowId);
    }
}
