package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.Auth;
import com.situ.emsvue.pojo.Role;
import com.situ.emsvue.pojo.VO.AuthVO;
import com.situ.emsvue.service.IRoleService;
import com.situ.emsvue.util.JwtUtil;
import com.situ.emsvue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping("/list")
    public Result list() {
        List<Role> roles = roleService.list();
        return Result.ok(roles);
    }

    @PostMapping("/edit/{id}")
    public Result edit(@PathVariable Integer id , @RequestHeader(name = "Authorization") String token) {
        System.out.println("-----------------------------------------"+id);
        List<AuthVO> authVOs = roleService.edit(id);
        return Result.ok(authVOs);
    }

}
