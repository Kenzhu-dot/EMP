package com.situ.ems_spring.mapper;

import com.situ.ems_spring.pojo.Users;

public interface UserMapper {
    Users login(int id, String password);
}
