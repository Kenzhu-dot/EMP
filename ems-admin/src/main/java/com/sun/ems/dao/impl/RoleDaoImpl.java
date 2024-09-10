package com.sun.ems.dao.impl;

import com.sun.ems.dao.IroleDao;
import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Role;
import com.sun.ems.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements IroleDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Integer> selectByRoleId(Integer roleId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select auth_id from role_to_auth where role_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, roleId);
        ResultSet rs = ps.executeQuery();
        List<Integer> list = new ArrayList<Integer>();
        while (rs.next()) {
            Integer authId = rs.getInt("auth_id");
            list.add(authId);
        }
        return list;
    }

    @Override
    public List<Role> LTById(Integer roleId) {
        String sql = "select * from role where id<?";
        return template.query(sql, new BeanPropertyRowMapper(Role.class) , roleId);
    }

    @Override
    public List<Auth> selectForAuth(int roleId) {
        String sql = "SELECT id,`name` FROM auth WHERE id IN (SELECT auth_id FROM role_to_auth WHERE role_id=?)";
        return template.query(sql, new BeanPropertyRowMapper(Auth.class), roleId);
    }

    @Override
    public void insertMidTable(Integer roleId, Integer authId) throws SQLException {
        String sql = "insert into role_to_auth(role_id,auth_id) values(?,?)";
        template.update(sql, roleId, authId);
    }

    @Override
    public void deleteMidTable(Integer roleId, Integer authId) throws SQLException {
        String sql = "delete from role_to_auth where role_id=? and auth_id=?";
        template.update(sql, roleId, authId);
    }
}
