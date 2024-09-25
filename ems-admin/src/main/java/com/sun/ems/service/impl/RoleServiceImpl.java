package com.sun.ems.service.impl;

import com.sun.ems.dao.IroleDao;
import com.sun.ems.dao.impl.RoleDaoImpl;
import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Role;
import com.sun.ems.service.IRoleService;

import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements IRoleService {
    IroleDao roleDao = new RoleDaoImpl();
    @Override
    public List<Integer> selectByRoleId(Integer roleId) {
        try {
            return roleDao.selectByRoleId(roleId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> LTById(Integer roleId) {
        return roleDao.LTById(roleId);
    }

    @Override
    public List<Auth> selectForAuth(String roleId) {
        return roleDao.selectForAuth(Integer.parseInt(roleId));
    }

    @Override
    public void updateMidTable(String roleId, String authId, String state) {
        if (state.equals("1")){
            try {
                roleDao.insertMidTable(Integer.parseInt(roleId),Integer.parseInt(authId));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                roleDao.deleteMidTable(Integer.parseInt(roleId),Integer.parseInt(authId));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
