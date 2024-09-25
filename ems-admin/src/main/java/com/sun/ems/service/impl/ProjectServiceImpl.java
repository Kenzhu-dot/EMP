package com.sun.ems.service.impl;

import com.sun.ems.dao.IemployeeDao;
import com.sun.ems.dao.IprojectDao;
import com.sun.ems.dao.impl.EmployeeDaoImpl;
import com.sun.ems.dao.impl.ProjectDaoImpl;
import com.sun.ems.listener.ExeclListener;
import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.ProjectPage;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IProjectService;
import com.sun.ems.util.ExcelUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProjectServiceImpl implements IProjectService {
    IprojectDao projectDao = new ProjectDaoImpl();
    @Override
    public PageResult selectByPage(XPage<ProjectPage> xPage) {
        List<ProjectPage> list = projectDao.selectByPage(xPage);
        Integer countByPage = projectDao.selectCountByPage(xPage);
        PageResult pageResult = new PageResult();
        pageResult.setCount(countByPage);
        pageResult.setData(list);
        pageResult.setCode(0);
        pageResult.setMsg("");
        return pageResult;
    }

    @Override
    public void exportExcel(HttpServletResponse resp) {
        List<ProjectPage> list = projectDao.selectByAll();
        ExcelUtil.exportExcel(resp,list,ProjectPage.class,"project");
    }

    @Override
    public void importExcel(HttpServletRequest req) {
        ExcelUtil.importExcel(req,new ExeclListener(),ProjectPage.class);
    }

    @Override
    public void addContract(Integer userId , String image) {
        IemployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.selectById(userId);
        Integer id = employee.getId();
        Integer leaderId = employee.getLeaderId();
        projectDao.addContract(id,leaderId,image);
    }
}
