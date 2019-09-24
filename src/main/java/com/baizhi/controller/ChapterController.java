package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.SizeUtil;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows,String albumId)throws Exception{
        Map<String, Object> map = chapterService.showAll(page, rows, albumId);
        return map;
    }


    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Chapter chapter,String albumId, String oper)throws Exception{
        chapter.setAlbum_id(albumId);
        String id=null;
        if(oper.equals("add")){
            id=chapterService.addOne(chapter);
            System.out.println(id);
        }
        if(oper.equals("edit")){
            id = chapterService.updateOne(chapter);
        }
        if(oper.equals("del")){
            chapterService.deleteOne(chapter.getId());
        }
        return id;
    }
    @RequestMapping("/chapterUpload")
    public void chapterUpload(MultipartFile url, String id, HttpServletRequest request)throws Exception{
        String realPath = request.getServletContext().getRealPath("/upload/music");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs(); //创建文件夹
        }
        String filename = url.getOriginalFilename();
        //给文件加一个时间戳
        String name=new Date().getTime()+"-"+filename;
        File file1= new File(realPath,name);
        url.transferTo(file1);
        String size = SizeUtil.getSize(file1);
        String duration = SizeUtil.getDuration(file1);
        //执行修改  修改文件的路径
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setDuration(duration);
        chapter.setUrl(name);
        chapter.setSizes(size);
        chapterService.updateOne(chapter);
    }

    @RequestMapping("/chapterDownload")
    public ResponseEntity<byte[]> chapterDownload(String filename, HttpSession session)throws Exception{
        ServletContext sc = session.getServletContext();
        String realPath = sc.getRealPath("/upload/music");
        File file = new File(realPath, filename);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        HttpHeaders h = new HttpHeaders();
        String downLoadName = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        h.setContentDispositionFormData("attachment",downLoadName);
        h.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(bytes,h, HttpStatus.CREATED);
    }

    @RequestMapping("/chapterPlay")
    public void chapterPlay(String filename, HttpServletRequest request, HttpServletResponse response)throws Exception{
//        ServletContext sc = session.getServletContext();
//        String realPath = sc.getRealPath("/upload/music");
//        File file = new File(realPath, filename);
//        byte[] bytes = FileUtils.readFileToByteArray(file);
//        HttpHeaders h = new HttpHeaders();
//        String downLoadName = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
//        h.setContentDispositionFormData("inline",downLoadName);
//        h.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return new ResponseEntity<byte[]>(bytes,h, HttpStatus.CREATED);
        //1.获取文件的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/music");
        //2.根据路径获取文件
        FileInputStream inputStream = new FileInputStream(new File(realPath,filename));
        //3.设置响应格式   响应头,文件名   attachment:以附件的形式下载   inline:在线打开
        response.setHeader("content-disposition","inline;filename="+ URLEncoder.encode(filename,"UTF-8"));
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //4.文件下载
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();
    }
}
