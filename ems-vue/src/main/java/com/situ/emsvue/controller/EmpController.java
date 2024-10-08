package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.entity.Emp;
import com.situ.emsvue.pojo.Query.EmpQuery;
import com.situ.emsvue.service.IEmpService;
import com.situ.emsvue.util.Result;
import com.situ.emsvue.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    IEmpService empService;

    @GetMapping("/list")
    public Result List(EmpQuery empQuery){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer roleId = (Integer) map.get("roleId");
        Map<String, Object> result = empService.list(empQuery , roleId);
        return Result.ok(result);
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Emp emp=empService.selectById(id);
        return Result.ok(emp);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        empService.deleteById(id);
        return Result.ok("删除成功");
    }

    @DeleteMapping("/deleteAll/{ids}")
    public Result deleteAll(@PathVariable Integer[] ids){
        empService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Emp emp){
        empService.add(emp);
        return Result.ok("添加成功");
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody Emp emp){
        empService.edit(emp);
        return Result.ok("更新成功");
    }

    @PutMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Integer id, @PathVariable Integer status){
        empService.updateStatus(id , status);
        return Result.ok();
    }
}
