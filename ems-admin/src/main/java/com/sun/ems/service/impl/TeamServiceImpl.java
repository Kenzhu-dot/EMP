package com.sun.ems.service.impl;

import com.sun.ems.dao.IteamDao;
import com.sun.ems.dao.IusersDao;
import com.sun.ems.dao.impl.TeamDaoImpl;
import com.sun.ems.dao.impl.UsersDaoImpl;
import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.Team;
import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IteamService;

import java.util.List;

public class TeamServiceImpl implements IteamService {
    IteamDao teamDao = new TeamDaoImpl();
    IusersDao usersDao = new UsersDaoImpl();
    @Override
    public PageResult selectByPage(XPage<Team> teamPage, Integer id, String pwd) {
        Users user = usersDao.login(id, pwd);
        List<Team> list = teamDao.selectByPage(teamPage);
        Integer countByPage = teamDao.selectCountByPage(teamPage);
        PageResult pageResult = new PageResult();
        pageResult.setCount(countByPage);
        pageResult.setData(list);
        pageResult.setCode(0);
        pageResult.setMsg("");
        return pageResult;
    }
}
