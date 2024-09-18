package com.situ.ems_spring.service.impl;

import com.situ.ems_spring.mapper.RoleMapper;
import com.situ.ems_spring.pojo.Auth;
import com.situ.ems_spring.pojo.Role;
import com.situ.ems_spring.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Integer> selectByRoleId(Integer roleId) {
        return roleMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Role> LTById(Integer roleId) {
       return roleMapper.LTById(roleId);
    }

    @Override
    public void updateMidTable(String roleId, String authId, String state) {
        if (state.equals("1")){
            roleMapper.insertMidTable(Integer.parseInt(roleId),Integer.parseInt(authId));
        }
        else{
            roleMapper.deleteMidTable(Integer.parseInt(roleId),Integer.parseInt(authId));
        }
    }

    @Override
    public List<Auth> selectForAuth(String roleId) {
        return roleMapper.selectForAuth(Integer.parseInt(roleId));
    }
}
