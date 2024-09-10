package com.sun.ems.dao.impl;

import com.sun.ems.dao.IteamDao;
import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.Team;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IteamService;
import com.sun.ems.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class TeamDaoImpl implements IteamDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Team> selectByPage(XPage<Team> teamPage) {
        String sql = "select * from project_team  where 1=1";
        List<Object> pageArray = new ArrayList<>();
        if (teamPage.getX()!=null){
            Team team = teamPage.getX();
            if (team.getId() != null) {
                sql += " and id=?";
                pageArray.add(team.getId());
            }
        }
        sql +=" limit ?,?";
        pageArray.add((teamPage.getPage()-1)*teamPage.getLimit());
        pageArray.add(teamPage.getLimit());
        return template.query(sql,new BeanPropertyRowMapper<>(Team.class),pageArray.toArray());
    }

    @Override
    public Integer selectCountByPage(XPage<Team> teamPage) {
        String sql = "select COUNT(*) from project_team  where 1=1";
        List<Object> pageArray = new ArrayList<>();
        if (teamPage.getX()!=null){
            Team team = teamPage.getX();
            if (team.getId() != null) {
                sql += " and id=?";
                pageArray.add(team.getId());
            }
        }
        return template.queryForObject(sql,Integer.class,pageArray.toArray());
    }
}
