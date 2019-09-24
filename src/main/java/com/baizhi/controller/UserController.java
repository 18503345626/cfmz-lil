package com.baizhi.controller;

import com.baizhi.entity.UserPro;
import com.baizhi.service.UserService;
import com.baizhi.util.PhoneMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //查询所有
    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows)throws Exception{
        Map<String, Object> map = userService.showAll(page, rows);
        return map;
    }

    //修改用户状态
    @RequestMapping("changeStatus")
    @ResponseBody
    public void changeStatus(String rowId)throws Exception{
        userService.changeStatus(rowId);
    }
    @RequestMapping("/outUser")
    @ResponseBody
    public Map<String,Object> outUser(HttpServletRequest request){
        Map<String,Object> map= userService.outUser(request);
        return map;
    }

    //用户统计
    @RequestMapping("/userEcharts")
    @ResponseBody
    public Map<String,Object> userEcharts(){
        Map<String,Object> map= userService.userEcharts();
        return map;
    }

    //userChina 用户分布
    @RequestMapping("/userChina")
    @ResponseBody
    public List<UserPro> userChina(){
        List<UserPro> list= userService.userChina();
        return list;
    }

    //userChina 用户分布
    @RequestMapping("/phoneNumber")
    @ResponseBody
    public String phoneNumber(String phone, HttpSession session){
        String random = PhoneMsgUtil.getRandom(6);
        session.setAttribute("phoneCode",random);
        String message = PhoneMsgUtil.getPhones(phone, random);
        System.out.println(message);
        return message;
    }
}
