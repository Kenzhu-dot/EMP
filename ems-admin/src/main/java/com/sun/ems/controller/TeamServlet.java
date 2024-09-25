package com.sun.ems.controller;

import com.sun.ems.pojo.Team;
import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.ITeamService;
import com.sun.ems.service.impl.TeamServiceImpl;
import com.sun.ems.util.JSONUtil;
import com.sun.ems.util.MyBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/team")
public class TeamServlet extends HttpServlet {
    ITeamService teamService = new TeamServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Team team = MyBeanUtil.copyToBean(req, Team.class);
        if ("".equals(team.getName())){
            team.setName(null);
        }
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        System.out.println(user);
        Integer id = user.getId();
        String pwd = user.getPwd();
        XPage<Team> teamPage = MyBeanUtil.copyToBean(req, XPage.class);
        teamPage.setX(team);
        PageResult result = teamService.selectByPage(teamPage,id,pwd);
        JSONUtil.toJSON(resp, result);
    }
}
