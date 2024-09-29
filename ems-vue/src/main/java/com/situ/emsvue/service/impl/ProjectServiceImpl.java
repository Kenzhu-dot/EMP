package com.situ.emsvue.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.emsvue.listener.ProjectExcelListener;
import com.situ.emsvue.mapper.ProjectMapper;
import com.situ.emsvue.pojo.Query.ProjectQuery;
import com.situ.emsvue.pojo.VO.ProjectVO;
import com.situ.emsvue.service.IProjectService;
import com.situ.emsvue.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Map<String,Object> list(ProjectQuery projectQuery) {
        PageHelper.startPage(projectQuery.getPage(), projectQuery.getLimit());
        List<ProjectVO> list = projectMapper.list(projectQuery);
        PageInfo<ProjectVO> pageInfo = new PageInfo<ProjectVO>(list);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total", pageInfo.getTotal());
        map.put("list", list);
        return map;
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        List<ProjectVO> list = projectMapper.selectAll();
        ExcelUtil.exportExcel(response, list, ProjectVO.class, "project");
    }

    @Override
    public void importExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), ProjectVO.class, new ProjectExcelListener(projectMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        projectMapper.deleteById(id);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            projectMapper.deleteById(id);
        }
    }

    @Override
    public void add(ProjectVO projectVO) {
        projectMapper.insert(projectVO);
    }

    @Override
    public ProjectVO selectById(Integer id) {
        return projectMapper.selectById(id);
    }

    @Override
    public void edit(ProjectVO projectVO) {
        projectMapper.edit(projectVO);
    }


}
