package com.baizhi.controller;

import com.baizhi.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/code")
public class CodeController {

    @RequestMapping("/code")
    public void getCode(HttpSession session, HttpServletResponse response)throws Exception{
        //生成随机数
        String code = ImageCodeUtil.getSecurityCode();
        session.setAttribute("code",code);
        //生成随机数图片
        BufferedImage image = ImageCodeUtil.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        //输出流输出
        ImageIO.write(image, "png",outputStream);
    }
}
