package com.situ.emsvue.service;

import com.situ.emsvue.pojo.Query.ProjectQuery;
import com.situ.emsvue.pojo.VO.ProjectVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IProjectService {
    Map<String,Object> list(ProjectQuery projectQuery);

    void exportExcel(HttpServletResponse response);

    void importExcel(MultipartFile file);

    void deleteById(Integer id);

    void deleteAll(Integer[] ids);

    void add(ProjectVO projectVO);

    ProjectVO selectById(Integer id);

    void edit(ProjectVO projectVO);
}
