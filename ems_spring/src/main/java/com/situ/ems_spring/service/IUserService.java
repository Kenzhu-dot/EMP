package com.situ.ems_spring.service;


import com.situ.ems_spring.pojo.Users;

public interface IUserService {
    Users login(Integer id, String password);
}
