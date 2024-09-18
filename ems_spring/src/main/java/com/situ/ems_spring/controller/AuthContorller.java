package com.situ.ems_spring.controller;


import com.situ.ems_spring.pojo.Auth;
import com.situ.ems_spring.pojo.VO.AuthVo;
import com.situ.ems_spring.service.IAuthService;
import com.situ.ems_spring.service.IRoleService;
import com.situ.ems_spring.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthContorller {
    @Autowired
    IAuthService authService;

    @Autowired
    IRoleService roleService;

    @RequestMapping("/selectAll")
    public Result selectAll(HttpServletRequest req){
        String id = req.getParameter("id");
        List<Auth> authList = authService.selectAll();
        List<Auth> userAuths = roleService.selectForAuth(id);
        List<AuthVo> authVos=new ArrayList<>();
        for (Auth auth : authList) {
            AuthVo authVo = new AuthVo();
            authVo.setAuth(auth);
            authVo.setState(0);
            authVos.add(authVo);
        }
        for (Auth auth : userAuths) {
            for (AuthVo authVo : authVos) {
                if (auth.equals(authVo.getAuth())){
                    authVo.setState(1);
                }
            }
        }
        return Result.ok(authVos);
    }
}
