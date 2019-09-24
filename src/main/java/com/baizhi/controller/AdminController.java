package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController  {
    @Autowired
    private AdminService adminService;

    //登录方法
    @RequestMapping("/login")
    @ResponseBody
    public  Map<String, Object> login(Admin admin, HttpSession session, String enCode, Model model)throws Exception{
        String code = (String)session.getAttribute("code");
        Map<String, Object> map = new HashMap<>();
        if(enCode.equalsIgnoreCase(code)) {
            try {
                Admin admin1 = adminService.login(admin);
                session.setAttribute("login",admin1);
                map.put("suc","200");
                map.put("message","账户登陆成功");
            }catch (Exception e){
                map.put("suc","400");
                map.put("message","用户名或密码错误");
            }
        }else {
            map.put("suc","400");
            map.put("message","验证码错误");
        }
        return map;
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception{
        session.removeAttribute("login");
        return "redirect:/login/login.jsp";
    }
}
