package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.VO.PieVO;
import com.situ.emsvue.pojo.VO.ProjectMonNumVO;

import java.util.List;

public interface ChartsMapper {
    List<PieVO> projectCompletion();

    List<ProjectMonNumVO> projectMonNum();

    List<ProjectMonNumVO> projectMonNumEnd();
}
