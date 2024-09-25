package com.sun.ems.service.impl;

import com.sun.ems.dao.IauthDao;
import com.sun.ems.dao.impl.AuthDaoImpl;
import com.sun.ems.pojo.Auth;
import com.sun.ems.service.IAuthService;

import java.util.List;

public class AuthServiceImpl implements IAuthService {
    IauthDao authDao =new AuthDaoImpl();
    @Override
    public Auth selectById(Integer id) {
        return authDao.selectById(id);
    }

    @Override
    public List<Auth> selectAll() {
        return authDao.selectAll();
    }
}
