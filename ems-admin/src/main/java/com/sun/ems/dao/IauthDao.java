package com.sun.ems.dao;

import com.sun.ems.pojo.Auth;

import java.util.List;

public interface IauthDao {
    Auth selectById(Integer id);

    List<Auth> selectAll();
}
