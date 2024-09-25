package com.sun.ems.controller;

import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.VO.AuthVo;
import com.sun.ems.service.IAuthService;
import com.sun.ems.service.IRoleService;
import com.sun.ems.service.impl.AuthServiceImpl;
import com.sun.ems.service.impl.RoleServiceImpl;
import com.sun.ems.util.JSONUtil;
import com.sun.ems.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    IAuthService authService = new AuthServiceImpl();
    IRoleService roleService = new RoleServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null || "".equals(method)) {
            method="selectAll";
        }
        switch (method) {
            case "selectAll":
                selectAll(req,resp);
                break;
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        List<Auth>authList = authService.selectAll();
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
        JSONUtil.toJSON(resp, Result.ok(authVos));
    }
}
