package com.sun.ems.dao.impl;


import com.sun.ems.dao.IusersDao;
import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements IusersDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Users login(Integer id , String pwd) {
        String sql = "select * from users where id=? and pwd=?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Users.class),id,pwd);
        }catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public List<Users> selectByPage(XPage<Users> XPage) {
        System.out.println(XPage);
        List<Object> pageArray = new ArrayList<>();
        Users user =XPage.getX();
        String sql = "select * from users where 1=1";
        if (user.getId() != null) {
            sql += " and id=?";
            pageArray.add(user.getId());
        }
        if (user.getRoleId()!= null) {
            sql += " and role_id=?";
            pageArray.add(user.getRoleId());
        }
        sql +=" limit ?,?";
        pageArray.add((XPage.getPage()-1)*XPage.getLimit());
        pageArray.add(XPage.getLimit());
        return template.query(sql,new BeanPropertyRowMapper<>(Users.class),pageArray.toArray());
    }

    @Override
    public Integer selectCountByPage(XPage<Users> XPage) {
        List<Object> pageArray = new ArrayList<>();
        Users user =XPage.getX();
        String sql = "select * from users where 1=1";
        if (user.getId() != null) {
            sql += " and id=?";
            pageArray.add(user.getId());
        }
        if (user.getRoleId()!= null) {
            sql += " and role_id=?";
            pageArray.add(user.getRoleId());
        }
        sql +=" limit ?,?";
        pageArray.add((XPage.getPage()-1)*XPage.getLimit());
        pageArray.add(XPage.getLimit());
        return template.queryForObject(sql,Integer.class,pageArray.toArray());
    }


}
