package com.situ.ems_spring.service;

import com.situ.ems_spring.pojo.Auth;
import com.situ.ems_spring.pojo.Role;

import java.util.List;

public interface IRoleService {
    List<Integer> selectByRoleId(Integer roleId);

    List<Role> LTById(Integer roleId);

    void updateMidTable(String roleId, String authId, String state);

    List<Auth> selectForAuth(String roleId);
}
