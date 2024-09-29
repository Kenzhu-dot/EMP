package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.Query.ProjectQuery;
import com.situ.emsvue.pojo.VO.ProjectVO;

import java.util.List;

public interface ProjectMapper {
    List<ProjectVO> list(ProjectQuery projectQuery);

    List<ProjectVO> selectAll();

    void insert(ProjectVO projectVO);

    void deleteById(Integer id);

    ProjectVO selectById(Integer id);

    void edit(ProjectVO projectVO);
}
