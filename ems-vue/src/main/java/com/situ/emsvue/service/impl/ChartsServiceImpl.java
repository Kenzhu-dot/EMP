package com.situ.emsvue.service.impl;

import com.situ.emsvue.mapper.ChartsMapper;
import com.situ.emsvue.pojo.VO.PieVO;
import com.situ.emsvue.pojo.VO.ProjectMonNumVO;
import com.situ.emsvue.service.IChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartsServiceImpl implements IChartsService {

    @Autowired
    ChartsMapper chartsMapper;

    @Override
    public List<PieVO> projectCompletion() {
        return chartsMapper.projectCompletion();
    }

    @Override
    public List<ProjectMonNumVO> projectMonNum() {
        return chartsMapper.projectMonNum();
    }

    @Override
    public List<ProjectMonNumVO> projectMonNumEnd() {
        return chartsMapper.projectMonNumEnd();
    }
}
