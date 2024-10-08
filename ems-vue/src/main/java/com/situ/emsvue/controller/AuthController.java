package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.VO.AuthVO;
import com.situ.emsvue.pojo.entity.Auth;
import com.situ.emsvue.service.IAuthService;
import com.situ.emsvue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @RequestMapping("/list")
    public Result list(){
        List<AuthVO> list = authService.list();
        return Result.ok(list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Auth auth){
        authService.add(auth);
        return Result.ok("添加成功");
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        authService.deleteById(id);
        return Result.ok("删除成功");
    }

    @DeleteMapping("/deleteAll/{ids}")
    public Result deleteAll(@PathVariable List<Integer> ids){
        authService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Auth auth = authService.selectById(id);
        return Result.ok(auth);
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody Auth auth){
        authService.edit(auth);
        return Result.ok("更新成功");
    }

    @PutMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Integer id, @PathVariable Integer status){
        authService.updateStatus(id,status);
        return Result.ok();
    }
}
