package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InterfaceController{
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @RequestMapping("first_page")
    @ResponseBody
    public Map<String, Object> first_page(String uid,String type,String sub_type)throws Exception{
        Map<String, Object> map = new HashMap<>();
        if(uid!=null){
            if(type.equals("all")){
                List<Banner> banners = bannerService.showBanners();
                map.put("banner",banners);
                Map<String, Object> map1 = albumService.showAll(1, 6);
                List<Album> rows = (List<Album>)map1.get("rows");
                map.put("album",rows);
                Map<String, Object> map2 = articleService.showAll(1, 6);
                List<Article> articles= (List<Article>)map2.get("rows");
                map.put("artical",articles);
            }else if(type.equals("wen")){
                Map<String, Object> map1 = albumService.showAll(1, 6);
                List<Album> rows = (List<Album>)map1.get("rows");
                map.put("album",rows);
            }else if(type.equals("si")){
                if("ssyj".equals(sub_type)){
                    Map<String, Object> map2 = articleService.showAll(1, 6);
                    List<Article> articles= (List<Article>)map2.get("rows");
                    map.put("artical",articles);
                }
                if("xmfy".equals(sub_type)){
                    Map<String, Object> map2 = articleService.showAll(1, 6);
                    List<Article> articles= (List<Article>)map2.get("rows");
                    map.put("artical",articles);
                }
            }
        }
        return map;

    }

}
