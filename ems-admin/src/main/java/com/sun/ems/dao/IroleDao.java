package com.sun.ems.dao;

import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface IroleDao {
    List<Integer> selectByRoleId(Integer roleId) throws SQLException;

    List<Role> LTById(Integer roleId);

    List<Auth> selectForAuth(int roleId);

    void insertMidTable(Integer roleId, Integer authId) throws SQLException;

    void  deleteMidTable(Integer roleId, Integer authId) throws SQLException;
}
