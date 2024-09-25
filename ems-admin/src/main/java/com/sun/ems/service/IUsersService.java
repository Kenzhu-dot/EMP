package com.sun.ems.service;


import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;

public interface IUsersService {
    Users login(Integer id, String pwd);

    PageResult selectByPage(XPage<Users> usersPage);
}
