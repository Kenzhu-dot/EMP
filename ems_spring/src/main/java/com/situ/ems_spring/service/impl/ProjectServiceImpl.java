package com.situ.ems_spring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.ems_spring.mapper.ProjectMapper;

import com.situ.ems_spring.pojo.Page.ProjectPage;
import com.situ.ems_spring.pojo.PageResult;
import com.situ.ems_spring.service.IProjectService;
import com.situ.ems_spring.util.ExcelUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;


    /*@Override*/
    /*public void importExcel(HttpServletRequest req) {
        ExcelUtil.importExcel(req,new ExeclListener(),ProjectPage.class);
    }

    @Override
    public void exportExcel(HttpServletResponse resp) {
        List<ProjectPage> list = projectMapper.selectByAll();
        ExcelUtil.exportExcel(resp,list,ProjectPage.class,"project");
    }*/

    @Override
    public PageResult selectByPage(ProjectPage projectPage, String page, String limit) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<ProjectPage> list = projectMapper.selectByPage(projectPage);
        PageInfo<ProjectPage> pageInfo = new PageInfo<>(list);
        Integer count = (int) pageInfo.getTotal();
        PageResult pageResult = new PageResult();
        pageResult.setCount(count);
        pageResult.setData(list);
        pageResult.setCode(0);
        pageResult.setMsg("");
        return pageResult;
    }
}
