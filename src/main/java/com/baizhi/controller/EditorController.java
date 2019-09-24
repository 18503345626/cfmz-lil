package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * kindleedit的相关操作
 * 上传和看所有图片
 */
@Controller
@RequestMapping("/editor")
public class EditorController {
    /*
    *
    * //成功时
        {
                "error" : 0,
                "url" : "http://www.example.com/path/to/file.ext"
        }
        //失败时
        {
                "error" : 1,
                "message" : "错误信息"
        }
    * */
    @RequestMapping("/uploadEditor")
    @ResponseBody
    public HashMap<String, Object> uploadEditor(MultipartFile photo, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String filename = photo.getOriginalFilename();
        String name = new Date().getTime() + "-" + filename;
        File file = new File(realPath, name);
        try {
            photo.transferTo(file);
            map.put("error", 0);
            String scheme = request.getScheme();//获取http
            String serverName = request.getServerName();//获取localhost
            int serverPort = request.getServerPort();//获取端口号8086
            String contextPath = request.getContextPath();//获取项目名/cmfz_lil
            map.put("url", scheme + "://" + serverName + ":" + serverPort + contextPath + "/upload/editor/" + name);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "上传失败");
        }
        return map;
    }
    /*
    *
    *
        {
          "moveup_dir_path": "",
          "current_dir_path": "",
          "current_url": "/ke4/php/../attached/",
          "total_count": 5,
          "file_list": [
            {
              "is_dir": false,
              "has_file": false,
              "filesize": 208736,
              "dir_path": "",
              "is_photo": true,
              "filetype": "jpg",
              "filename": "1241601537255682809.jpg",
              "datetime": "2018-06-06 00:36:39"
            }
          ]
        }
    * */
    @RequestMapping("queryPhotos")
    @ResponseBody
    public HashMap<String, Object> queryPhotos(HttpServletRequest request)throws Exception{
        HashMap<String, Object> maps = new HashMap<>();
        List<Object> lists = new ArrayList<>();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");//获取绝对路径
        File file = new File(realPath);
        String[] list = file.list();
        for (String s : list) {
//            {
//                "is_dir": false,
//                    "has_file": false,
//                    "filesize": 208736,
//                    "dir_path": "",
//                    "is_photo": true,
//                    "filetype": "jpg",
//                    "filename": "1241601537255682809.jpg",
//                    "datetime": "2018-06-06 00:36:39"
//            }
            HashMap<Object, Object> map = new HashMap<>();
            map.put("is_dir",false);
            map.put("has_file",false);
            File file1 = new File(realPath, s);
            map.put("filesize",file1.length());//获取大小以字节为单位
            map.put("is_photo",true);
            String extension = FilenameUtils.getExtension(s);
            map.put("filetype",extension);
            map.put("filename",s);
           //文件名获取日期类型
            String[] split = s.split("-");
            long l = Long.parseLong(split[0]);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = dateFormat.format(l);
            map.put("datetime",format);
            lists.add(map);
        }
//        "moveup_dir_path": "",
//                "current_dir_path": "",
//                "current_url": "/ke4/php/../attached/",
//                "total_count": 5,
//                "file_list":

        maps.put("current_url","http://localhost:8086/cmfz_lil/upload/editor/");
        maps.put("total_count",lists.size());
        maps.put("file_list",lists);
        return maps;
    }
}
