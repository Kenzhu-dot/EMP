package com.situ.ems_spring.service.impl;

import com.situ.ems_spring.mapper.AuthMapper;
import com.situ.ems_spring.pojo.Auth;
import com.situ.ems_spring.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    AuthMapper authMapper;

    @Override
    public Auth selectById(Integer authId) {
        return authMapper.selectById(authId);
    }

    @Override
    public List<Auth> selectAll() {
        return authMapper.selectAll();
    }
}
