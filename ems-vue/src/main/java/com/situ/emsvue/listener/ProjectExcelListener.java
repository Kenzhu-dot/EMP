package com.situ.emsvue.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.situ.emsvue.mapper.ProjectMapper;
import com.situ.emsvue.pojo.VO.ProjectVO;


public class ProjectExcelListener extends AnalysisEventListener<ProjectVO> {

    private ProjectMapper projectMapper;

    public ProjectExcelListener(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }
    @Override
    public void invoke(ProjectVO projectVO, AnalysisContext analysisContext) {
        System.out.println(projectVO);
        projectVO.setId(null);
        projectMapper.insert(projectVO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
