package com.situ.ems_spring.service;

import com.situ.ems_spring.pojo.Auth;

import java.util.List;

public interface IAuthService {
    Auth selectById(Integer authId);

    List<Auth> selectAll();
}
