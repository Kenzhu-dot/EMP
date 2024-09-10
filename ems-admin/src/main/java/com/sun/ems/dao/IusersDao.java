package com.sun.ems.dao;


import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.XPage;

import java.util.List;

public interface IusersDao {
    Users login(Integer id , String pwd);

    List<Users> selectByPage(XPage<Users> usersPage);

    Integer selectCountByPage(XPage<Users> usersPage);
}
