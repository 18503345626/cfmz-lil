package com.baizhi.service.impl;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDAO adminDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    public Admin login(Admin admin) {
        Admin admin1 = adminDAO.selectByAdminnameAndPassword(admin);
        if (admin1 == null) throw new RuntimeException("登陆失败，用户名和密码错误");
        return admin1;
    }
}
