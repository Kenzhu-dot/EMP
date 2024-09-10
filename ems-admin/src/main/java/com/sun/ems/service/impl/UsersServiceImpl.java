package com.sun.ems.service.impl;


import com.sun.ems.dao.IusersDao;
import com.sun.ems.dao.impl.UsersDaoImpl;
import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IusersService;

import java.util.List;

public class UsersServiceImpl implements IusersService {


    IusersDao usersDao = new UsersDaoImpl();

    @Override
    public Users login(Integer id , String pwd) {
        return usersDao.login(id , pwd);
    }

    @Override
    public PageResult selectByPage(XPage<Users> usersPage) {
        List<Users>list = usersDao.selectByPage(usersPage);
        Integer countByPage = usersDao.selectCountByPage(usersPage);
        PageResult pageResult = new PageResult();
        pageResult.setCount(countByPage);
        pageResult.setData(list);
        pageResult.setCode(0);
        pageResult.setMsg("");
        return pageResult;
    }
}
