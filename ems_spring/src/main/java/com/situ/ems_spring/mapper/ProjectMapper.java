package com.situ.ems_spring.mapper;

import com.situ.ems_spring.pojo.Page.ProjectPage;

import java.util.List;

public interface ProjectMapper {
    List<ProjectPage> selectByPage(ProjectPage projectPage);
}
