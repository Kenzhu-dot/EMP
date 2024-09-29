package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.Query.ProjectQuery;
import com.situ.emsvue.pojo.VO.ProjectVO;
import com.situ.emsvue.service.IProjectService;
import com.situ.emsvue.util.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @GetMapping("/list")
    public Result list(ProjectQuery projectQuery) {
        Map<String,Object> map = projectService.list(projectQuery);
        return Result.ok(map);
    }

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
        projectService.exportExcel(response);
    }

    @PostMapping("/importExcel")
    public Result importExcel(MultipartFile file) {
        projectService.importExcel(file);
        return Result.ok();
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        projectService.deleteById(id);
        return Result.ok("删除成功");
    }

    @DeleteMapping("/deleteAll/{ids}")
    public Result deleteAll(@PathVariable Integer[] ids) {
        projectService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @PostMapping("/add")
    public Result add(@RequestBody ProjectVO projectVO) {
        projectService.add(projectVO);
        return Result.ok("添加成功");
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ProjectVO projectVO = projectService.selectById(id);
        return Result.ok(projectVO);
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody ProjectVO projectVO) {
        projectService.edit(projectVO);
        return Result.ok("更新成功");
    }
}
