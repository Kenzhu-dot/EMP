package com.sun.ems.controller;

import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.ProjectPage;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IprojectService;
import com.sun.ems.service.impl.ProjectServiceImpl;
import com.sun.ems.util.CookieUtil;
import com.sun.ems.util.JSONUtil;
import com.sun.ems.util.MyBeanUtil;
import com.sun.ems.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
    IprojectService projectService = new ProjectServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null || "".equals(method)) {
            method="selectByPage";
        }
        switch (method) {
            case "selectByPage":
                selectByPage(req,resp);
                break;
            case "deleteById":
                deleteById(req,resp);
                break;
            case "deleteAll":
                deleteAll(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "addContract":
                addContract(req,resp);
                break;
            case "selectById":
                selectById(req,resp);
                break;
            case "exportExcel":
                exportExcel(req,resp);
                break;
            case "importExcel":
                importExcel(req,resp);
                break;
        }
    }

    private void importExcel(HttpServletRequest req, HttpServletResponse resp) {
        projectService.importExcel(req);
        JSONUtil.toJSON(resp,Result.ok("导出成功"));
    }

    private void exportExcel(HttpServletRequest req, HttpServletResponse resp) {
        projectService.exportExcel(resp);
//        JSONUtil.toJSON(resp, Result.ok("导出成功"));
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void addContract(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        Integer userId = user.getId();
        String image = req.getParameter("image");
        System.out.println(image);
        projectService.addContract(userId,image);
        JSONUtil.toJSON(resp,Result.ok());
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        ProjectPage projectPage = MyBeanUtil.copyToBean(req, ProjectPage.class);
        if ("".equals(projectPage.getName())){
            projectPage.setName(null);
        }
        System.out.println("projectPage"+projectPage);
        XPage<ProjectPage> XPage = MyBeanUtil.copyToBean(req, XPage.class);
        XPage.setX(projectPage);
        PageResult result = projectService.selectByPage(XPage);
        JSONUtil.toJSON(resp, result);
    }
}
