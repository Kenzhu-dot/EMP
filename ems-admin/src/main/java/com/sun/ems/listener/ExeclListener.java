package com.sun.ems.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sun.ems.dao.IprojectDao;
import com.sun.ems.dao.impl.ProjectDaoImpl;
import com.sun.ems.pojo.Project;
import com.sun.ems.pojo.page.ProjectPage;


public class ExeclListener extends AnalysisEventListener<ProjectPage> {

    IprojectDao projectDao = new ProjectDaoImpl();
    @Override
    public void invoke(ProjectPage projectPage, AnalysisContext analysisContext) {
        projectDao.addExcel(projectPage);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
