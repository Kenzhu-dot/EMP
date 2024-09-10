package com.sun.ems.dao.impl;

import com.sun.ems.dao.IauthDao;
import com.sun.ems.pojo.Auth;
import com.sun.ems.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AuthDaoImpl implements IauthDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Auth selectById(Integer id) {
        String sql = "select * from auth where id = ?";
         return template.queryForObject(sql, new BeanPropertyRowMapper<>(Auth.class), id);
    }

    @Override
    public List<Auth> selectAll() {
        String sql = "select * from auth";
        return template.query(sql, new BeanPropertyRowMapper<>(Auth.class));
    }
}
