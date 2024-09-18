package com.situ.ems_spring.mapper;

import com.situ.ems_spring.pojo.Auth;
import com.situ.ems_spring.pojo.Role;

import java.util.List;

public interface RoleMapper {

    List<Integer> selectByRoleId(Integer roleId);

    List<Role> LTById(Integer roleId);

    void insertMidTable(int roleId, int authId);

    void deleteMidTable(int roleId, int authId);

    List<Auth> selectForAuth(int roleId);
}
