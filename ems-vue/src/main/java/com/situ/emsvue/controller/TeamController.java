package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.entity.Team;
import com.situ.emsvue.service.ITeamService;
import com.situ.emsvue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    ITeamService teamService;

    @RequestMapping("/selectAll")
    public Result selectAll(){
        List<Team> list = teamService.selectAll();
        return Result.ok(list);
    }
}
