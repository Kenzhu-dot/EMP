package com.sun.ems.service;

import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Role;

import java.util.List;

public interface IRoleService {
    List<Integer> selectByRoleId(Integer roleId);
    List<Role> LTById(Integer roleId);

    List<Auth> selectForAuth(String roleId);

    void updateMidTable(String roleId, String authId, String state);
}
