package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.User;
import com.baizhi.entity.UserEcharts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO  {
    List<User> selectAll(@Param("page")Integer page, @Param("rows")Integer rows);
    Integer selectCount();
    void insertOne(User user);
    void updateOne(User user);
    void deleteById(String id);

    User selectById(String rowId);//根据Id查询

    List<User> searchAll();//不分页查询所有
    //按性别查询月份和个数
    List<UserEcharts> selectBySex(@Param("sex") String sex);

    Integer selectBySexAndMonth(@Param("sex")String sex,@Param("month")String month);

    List<City> selectGroupByCity(String sex);
}
