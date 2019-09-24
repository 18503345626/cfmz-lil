package com.baizhi.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.City;
import com.baizhi.entity.User;
import com.baizhi.entity.UserEcharts;
import com.baizhi.entity.UserPro;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;
    @Override
    public Map<String, Object> showAll(Integer page, Integer rows) {
        List<User> users = userDAO.selectAll(page, rows);
        Map<String, Object>  map = new HashMap<>();
        map.put("rows",users);
        Integer count = userDAO.selectCount();
        map.put("records",count);
        Integer total= count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public String addOne(User user) {
        return null;
    }

    @Override
    public String updateOne(User user) {
        return null;
    }

    @Override
    public void deleteOne(String id) {

    }

    //修改用户状态
    @Override
    public void changeStatus(String rowId) {
        User user=userDAO.selectById(rowId);
        if(user.getStatus().equals("冻结")){
            user.setStatus("可使用");
        }else {
            user.setStatus("冻结");
        }
        userDAO.updateOne(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> outUser(HttpServletRequest request){
        String realPath = request.getServletContext().getRealPath("/upload/photo");
        HashMap<String, Object> map = new HashMap<>();
        List<User> list= userDAO.searchAll();
        ArrayList<User> users = new ArrayList<>();
        for (User user : list) {
            user.setAvatar(realPath+"//"+user.getAvatar());
            users.add(user);
        }
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户详细信息", "用户表"), User.class, users);
            workbook.write(new FileOutputStream(new File("E:/培训文件/用户信息.xls")));
            map.put("success",200);
            map.put("message","导出成功");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            map.put("success",400);
            map.put("message","导出失败");
        }
        return map;
    }

    //用户统计
    @Override
    public Map<String, Object> userEcharts() {
        List<UserEcharts> boys=userDAO.selectBySex("男");
        List<UserEcharts> girls=userDAO.selectBySex("女");
        Set<String> setUnDate = new HashSet<>();
        for (UserEcharts boy : boys) {
            setUnDate.add(boy.getMonth());
        }
        for (UserEcharts girl : girls) {
            setUnDate.add(girl.getMonth());
        }
        TreeSet<Integer> months = new TreeSet<>();
        for (String s : setUnDate) {
            String[] split = s.split("-");
            int i = Integer.parseInt(split[1]);
            months.add(i);
        }
        ArrayList<Integer> boyss = new ArrayList<>();
        ArrayList<Integer> girlss =new ArrayList<>();
        ArrayList<String> monthss = new ArrayList<>();
        for (Integer month : months) {
            monthss.add(month+"月");
            Integer boyx=null;
            Integer girlx =null;
            if(month<10){
                boyx= userDAO.selectBySexAndMonth("男", "-0"+month + "-");
                girlx = userDAO.selectBySexAndMonth("女", "-0"+month + "-");
            }else {
                boyx= userDAO.selectBySexAndMonth("男", "-"+month + "-");
                girlx = userDAO.selectBySexAndMonth("女", "-"+month + "-");
            }
            boyss.add(boyx);
            girlss.add(girlx);
//            if(boyx.size()!=0&&girlx.size()!=0){
//                boyss.add(boyx.get(0).getCount());
//                girlss.add(girlx.get(0).getCount());
//            }else if(boyx.size()==0&&girlx.size()!=0){
//                boyss.add(0);
//                girlss.add(girlx.get(0).getCount());
//            }else if(boyx.size()!=0&&girlx.size()==0){
//                boyss.add(boyx.get(0).getCount());
//                girlss.add(0);
//            }else {
//                boyss.add(0);
//                girlss.add(0);
//            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("month",monthss);
        map.put("boys",boyss);
        map.put("girls",girlss);
        return map;
    }

    //用户分布
    @Override
    public List<UserPro> userChina() {
        List<City> boys = userDAO.selectGroupByCity("男");
        List<City> girls = userDAO.selectGroupByCity("女");
        UserPro boyPro = new UserPro();
        UserPro girlPro = new UserPro();
        boyPro.setCitys(boys);
        boyPro.setTitle("小男生");
        girlPro.setCitys(girls);
        girlPro.setTitle("小姑娘");
        List<UserPro> userPros = new ArrayList<>();
        userPros.add(boyPro);
        userPros.add(girlPro);
        return userPros;
    }
}
