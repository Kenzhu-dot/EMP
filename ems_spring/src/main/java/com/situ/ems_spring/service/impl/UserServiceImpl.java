package com.situ.ems_spring.service.impl;

import com.situ.ems_spring.mapper.UserMapper;
import com.situ.ems_spring.pojo.Users;
import com.situ.ems_spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users login(Integer id, String password) {
        System.out.println("UserServiceImpl.login");
        return userMapper.login(id,password);
    }
}
