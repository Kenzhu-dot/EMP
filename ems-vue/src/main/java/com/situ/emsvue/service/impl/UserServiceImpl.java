package com.situ.emsvue.service.impl;


import com.situ.emsvue.mapper.RoleMapper;
import com.situ.emsvue.mapper.UserMapper;
import com.situ.emsvue.pojo.Auth;
import com.situ.emsvue.pojo.Info.LoginInfo;
import com.situ.emsvue.pojo.Users;
import com.situ.emsvue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Users login(LoginInfo loginInfo) {
        return userMapper.login(loginInfo);
    }

    @Override
    public List<Auth> userInform(Integer roleId) {
        return roleMapper.userInform(roleId);
    }

    @Override
    public Users selectOldPassword(Integer id, String oldPassword) {
        return userMapper.selectOldPassword(id,oldPassword);
    }

    @Override
    public void updatePassword(Integer id, String newPassword) {
        userMapper.updatePassword(id , newPassword);
    }
}
