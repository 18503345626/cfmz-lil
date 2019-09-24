package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    @Excel(name="id")
    private String id;
    @Excel(name="头像",type = 2)
    private String avatar;
    @Excel(name="手机号")
    private String phone;
    @Excel(name="密码")
    private String password;
    @Excel(name="状态")
    private String status;
    @Excel(name="姓名")
    private String name;
    @Excel(name="法名")
    private String law_name;
    @Excel(name="性别")
    private String sex;
    @Excel(name="城市")
    private String city;
    @Excel(name="标识")
    private String sign;
    @Excel(name="注册日期",format = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date crea_date;
    @Excel(name="上师id")
    private String guru_id;
}
