package com.sun.ems.dao.impl;

import com.sun.ems.dao.IprojectDao;
import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.Project;
import com.sun.ems.pojo.page.ProjectPage;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements IprojectDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<ProjectPage> selectByPage(XPage<ProjectPage> xPage) {
        String sql = "select *, (SELECT `name` FROM project_team WHERE project.project_team_id=project_team.id) AS team_name from project  where 1=1";
        List<Object> pageArray = new ArrayList<>();
        ProjectPage projectPage = new ProjectPage();
        if (xPage.getX()!=null){
            projectPage = xPage.getX();
        }
            if (projectPage.getId() != null) {
                sql += " and id=?";
                pageArray.add(projectPage.getId());
            }
            if (projectPage.getName() != null) {
                sql += " and name like ?";
                pageArray.add("%" + projectPage.getName() + "%");
            }
        if (projectPage.getCreateTime()!=null &&projectPage.getEndTime()!=null){
            sql += " and end_time between ? and ?";
            pageArray.add(projectPage.getCreateTime());
            pageArray.add(projectPage.getEndTime());
        }
        sql +=" limit ?,?";
        pageArray.add((xPage.getPage()-1)*xPage.getLimit());
        pageArray.add(xPage.getLimit());
        return template.query(sql,new BeanPropertyRowMapper<>(ProjectPage.class),pageArray.toArray());
    }

    @Override
    public Integer selectCountByPage(XPage<ProjectPage> xPage) {
        List<Object> pageArray = new ArrayList<>();
        String sql = "select count(*) from project where 1=1";
        ProjectPage projectPage = new ProjectPage();
        if (xPage.getX()!=null) {
            projectPage = xPage.getX();
        }
        if (projectPage.getId() != null) {
            sql += " and id=?";
            pageArray.add(projectPage.getId());
        }
        if (projectPage.getName() != null) {
            sql += " and name like ?";
            pageArray.add("%" + projectPage.getName() + "%");
        }
        if (projectPage.getCreateTime()!=null &&projectPage.getEndTime()!=null){
            sql += " and end_time between ? and ?";
            pageArray.add(projectPage.getCreateTime());
            pageArray.add(projectPage.getEndTime());
        }
        return template.queryForObject(sql,Integer.class,pageArray.toArray());
    }

    @Override
    public List<ProjectPage> selectByAll() {
        String sql="select *, (SELECT `name` FROM project_team WHERE project.project_team_id=project_team.id) AS team_name from project ";
        return template.query(sql,new BeanPropertyRowMapper<>(ProjectPage.class));
    }

    @Override
    public void addExcel(ProjectPage projectPage) {
        String sql = "INSERT INTO project(name,project_progress,project_size,create_time,end_time,project_team_id,state) VALUES(?,?,?,?,?,?,?)";
        template.update(sql,projectPage.getName(),projectPage.getProjectProgress(),projectPage.getProjectSize(),projectPage.getCreateTime(),projectPage.getEndTime(),projectPage.getProjectTeamId(),projectPage.getState());
    }

    @Override
    public void add(ProjectPage projectPage) {
        String sql = "INSERT INTO project(name,project_progress,project_size,create_time,end_time,project_team_id,state) VALUES(?,?,?,?,?,?,?)";
        template.update(sql,projectPage.getName(),projectPage.getProjectProgress(),projectPage.getProjectSize(),projectPage.getCreateTime(),projectPage.getEndTime(),projectPage.getProjectTeamId(),projectPage.getState());
    }

    @Override
    public void addContract(Integer id , Integer leaderId , String image) {
        String sql = "INSERT INTO contract (em_id,image,leader_id) VALUES(?,?,?)";
        template.update(sql,id,image,leaderId);
    }
}
