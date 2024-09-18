package com.situ.ems_spring.mapper;

import com.situ.ems_spring.pojo.Auth;

import java.util.List;

public interface AuthMapper {
    Auth selectById(Integer authId);

    List<Auth> selectAll();
}
