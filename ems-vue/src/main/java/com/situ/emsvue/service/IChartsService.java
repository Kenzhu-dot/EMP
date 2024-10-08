package com.situ.emsvue.service;

import com.situ.emsvue.pojo.VO.PieVO;
import com.situ.emsvue.pojo.VO.ProjectMonNumVO;

import java.util.List;
import java.util.Map;

public interface IChartsService {
    List<PieVO> projectCompletion();

    List<ProjectMonNumVO> projectMonNum();

    List<ProjectMonNumVO> projectMonNumEnd();
}
