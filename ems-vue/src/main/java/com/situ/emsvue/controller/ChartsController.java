package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.VO.PieVO;
import com.situ.emsvue.pojo.VO.ProjectMonNumVO;
import com.situ.emsvue.service.IChartsService;
import com.situ.emsvue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/charts")
public class ChartsController {

    @Autowired
    IChartsService chartsService;

    @GetMapping("/projectCompletion")
    public Result projectCompletion() {
        List<PieVO> pieVOS =  chartsService.projectCompletion();
        return Result.ok(pieVOS);
    }

    @GetMapping("/projectMonNum")
    public Result projectMonNum() {
        List<ProjectMonNumVO>list=chartsService.projectMonNum();
        return Result.ok(list);
    }

    @GetMapping("/projectMonNumEnd")
    public Result projectMonNumEnd() {
        List<ProjectMonNumVO>list=chartsService.projectMonNumEnd();
        return Result.ok(list);
    }
}
