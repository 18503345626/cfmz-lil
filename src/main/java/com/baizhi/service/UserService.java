package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserPro;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> showAll(Integer page , Integer rows);

    String addOne(User user);

    String updateOne(User user);

    void deleteOne(String id);

    void changeStatus(String rowId);
    //导出用户
    Map<String, Object> outUser(HttpServletRequest request);

    Map<String, Object> userEcharts();

    List<UserPro> userChina();//用户分布
}
