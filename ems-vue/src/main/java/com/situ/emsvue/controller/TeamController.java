package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.entity.Team;
import com.situ.emsvue.service.ITeamService;
import com.situ.emsvue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/list")
    public Result list(){
        List<Team> list = teamService.selectAll();
        return Result.ok(list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Team team){
        teamService.add(team);
        return Result.ok("添加成功");
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deletedById(@PathVariable Integer id){
        teamService.deletedById(id);
        return Result.ok("success");
    }

    @DeleteMapping("/deleteAll/{ids}")
    public Result deleteAll(@PathVariable Integer[] ids){
        teamService.deleteAll(ids);
        return Result.ok("success");
    }

    @GetMapping("/detail/{id}")
    public Result detailById(@PathVariable Integer id){
        Map<String , Object> map = teamService.detailById(id);
        return Result.ok(map);
    }
}
