package com.sun.ems.service;

import com.sun.ems.pojo.Auth;

import java.util.List;

public interface IauthService {
    Auth selectById(Integer id);

    List<Auth> selectAll();
}
