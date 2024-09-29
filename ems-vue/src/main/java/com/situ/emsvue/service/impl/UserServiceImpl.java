package com.situ.emsvue.service.impl;


import com.situ.emsvue.mapper.EmpMapper;
import com.situ.emsvue.mapper.RoleMapper;
import com.situ.emsvue.mapper.UserMapper;
import com.situ.emsvue.pojo.entity.Auth;
import com.situ.emsvue.pojo.entity.Emp;
import com.situ.emsvue.pojo.Info.LoginInfo;
import com.situ.emsvue.pojo.entity.Users;
import com.situ.emsvue.pojo.VO.UserInfoVO;
import com.situ.emsvue.service.IUserService;
import com.situ.emsvue.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    EmpMapper empMapper;
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

    @Override
    public UserInfoVO allInfo(Integer id) {
       return userMapper.allInfo(id);
    }

    @Override
    public void edit(UserInfoVO userInfoVO) {
        Users user = new Users();
        user.setImage(userInfoVO.getImage());
        Emp emp = new Emp();
        emp.setName(userInfoVO.getName());
        emp.setGender(userInfoVO.getGender());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        user.setId(id);
        emp.setId(id);
        empMapper.edit(emp);
        userMapper.edit(user);
    }
}
