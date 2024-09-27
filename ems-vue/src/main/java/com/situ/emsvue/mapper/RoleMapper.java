package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.Auth;
import com.situ.emsvue.pojo.Role;

import java.util.List;

public interface RoleMapper {
    List<Auth> userInform(Integer roleId);

    List<Role> list();

}
