package com.baizhi;

import com.alibaba.fastjson.JSON;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.City;
import com.baizhi.entity.UserEcharts;
import com.baizhi.entity.UserPro;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoEasy {
    @Resource
    private UserDAO userDAO;

    //测试用户分布的GoEasy
    @Test
    public void test1(){
        // 参数：服务器地址    ,  AppKey:commonKey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-85bd4afa4c4d4f3db9e61c7824b1dd74");
        List<City> boys = userDAO.selectGroupByCity("男");
        List<City> girls = userDAO.selectGroupByCity("女");
        UserPro boyPro = new UserPro();
        UserPro girlPro = new UserPro();
        boyPro.setCitys(boys);
        boyPro.setTitle("小男生");
        girlPro.setCitys(girls);
        girlPro.setTitle("小姑娘");
        City city = new City();
        city.setName("北京");
        city.setValue(10);
        boys.add(city);
        List<UserPro> userPros = new ArrayList<>();
        userPros.add(boyPro);
        userPros.add(girlPro);
        String s = JSON.toJSONString(userPros);

        //参数：管道标识，发送内容
        goEasy.publish("cmfz-lil",s);
    }

    //测试用户统计的GoEasy
    @Test
    public void test2(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-85bd4afa4c4d4f3db9e61c7824b1dd74");
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
            boyss.add(boyx+1);
            girlss.add(girlx);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("month",monthss);
        map.put("boys",boyss);
        map.put("girls",girlss);
        String s = JSON.toJSONString(map);
        goEasy.publish("cmfz-lil",s);
    }
}
