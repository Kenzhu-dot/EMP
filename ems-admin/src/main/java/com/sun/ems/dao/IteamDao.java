package com.sun.ems.dao;

import com.sun.ems.pojo.Team;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;

import java.util.List;

public interface IteamDao {
    List<Team> selectByPage(XPage<Team> teamPage);

    Integer selectCountByPage(XPage<Team> teamPage);
}
