package com.situ.ems_spring.controller;

import com.situ.ems_spring.pojo.Page.ProjectPage;
import com.situ.ems_spring.pojo.PageResult;
import com.situ.ems_spring.service.IProjectService;
import com.situ.ems_spring.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    /*@RequestMapping("/importExcel")
    public Result importExcel(HttpServletRequest req) {
        projectService.importExcel(req);
        return Result.ok("导出成功");
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse resp) {
        projectService.exportExcel(resp);
    }*/

    @RequestMapping("/selectByPage")
    public PageResult selectByPage(ProjectPage projectPage , String page , String limit) {
        PageResult result = projectService.selectByPage(projectPage , page ,limit);
        return result;
    }

}
