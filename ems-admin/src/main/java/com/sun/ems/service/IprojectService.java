package com.sun.ems.service;

import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.ProjectPage;
import com.sun.ems.pojo.page.XPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IprojectService {
    PageResult selectByPage(XPage<ProjectPage> xPage);

    void exportExcel(HttpServletResponse resp);

    void importExcel(HttpServletRequest req);

    void addContract(Integer userId , String image);
}
