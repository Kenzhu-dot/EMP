package com.situ.ems_spring.service;

import com.situ.ems_spring.pojo.Page.ProjectPage;
import com.situ.ems_spring.pojo.PageResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IProjectService {
    /*void importExcel(HttpServletRequest req);

    void exportExcel(HttpServletResponse resp);*/

    PageResult selectByPage(ProjectPage projectPage, String page, String limit);
}
