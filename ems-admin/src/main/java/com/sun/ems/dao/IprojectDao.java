package com.sun.ems.dao;

import com.sun.ems.pojo.Project;
import com.sun.ems.pojo.page.ProjectPage;
import com.sun.ems.pojo.page.XPage;

import java.util.List;

public interface IprojectDao {
    List<ProjectPage> selectByPage(XPage<ProjectPage> xPage);

    Integer selectCountByPage(XPage<ProjectPage> xPage);

    List<ProjectPage> selectByAll();

    void addExcel(ProjectPage projectPage);

    void add(ProjectPage projectPage);

    void addContract(Integer id , Integer leaderId , String image);
}
