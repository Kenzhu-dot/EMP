package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.Auth;
import com.situ.emsvue.pojo.VO.AuthVO;
import com.situ.emsvue.service.IAuthService;
import com.situ.emsvue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
