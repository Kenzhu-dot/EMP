package com.sun.ems.service;

import com.sun.ems.pojo.Team;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;

public interface IteamService {
    PageResult selectByPage(XPage<Team> teamPage, Integer id, String pwd);
}
