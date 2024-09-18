package com.situ.ems_spring.controller;


import com.situ.ems_spring.pojo.Auth;
import com.situ.ems_spring.pojo.Role;
import com.situ.ems_spring.pojo.Users;
import com.situ.ems_spring.service.IRoleService;
import com.situ.ems_spring.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("/selectAll")
    public Result selectAll(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        List<Role> roles = roleService.LTById(user.getRoleId());
        return Result.ok(roles);
    }

    @RequestMapping("/updateMidTable")
    public Result updateMidTable(HttpServletRequest req){
        String roleId = req.getParameter("roleId");
        String authId = req.getParameter("authId");
        String state = req.getParameter("state");
        roleService.updateMidTable(roleId,authId,state);
        return Result.ok("修改成功");
    }

    @RequestMapping("/selectForAuth")
    public Result selectForAuth(HttpServletRequest req){
        String roleId = req.getParameter("roleId");
        List<Auth>authList = roleService.selectForAuth(roleId);
        return Result.ok(authList);
    }

    @RequestMapping("/LTById")
    public Result LTById(HttpSession session){
        System.out.println("RoleController.LTById");
        Users user = (Users) session.getAttribute("user");
        List<Role> roles = roleService.LTById(user.getRoleId());
        return Result.ok(roles);
    }

}
