package com.situ.ems_spring.controller;


import com.situ.ems_spring.pojo.Auth;
import com.situ.ems_spring.pojo.Page.LoginInfor;
import com.situ.ems_spring.pojo.Users;
import com.situ.ems_spring.service.IAuthService;
import com.situ.ems_spring.service.IRoleService;
import com.situ.ems_spring.service.IUserService;
import com.situ.ems_spring.util.CookieUtil;
import com.situ.ems_spring.util.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IAuthService authService;


    @RequestMapping("/login")
    public Result login(LoginInfor loginInfor, HttpSession session , HttpServletRequest request , HttpServletResponse response) {
        System.out.println("AdminServlet.login");
        Integer id = loginInfor.getId();
        String password = loginInfor.getPassword();
        String code = loginInfor.getCode();
       /* String codeInSession = (String) session.getAttribute("codeInSession");
        if (!codeInSession.equalsIgnoreCase(code)) {
            return Result.error("验证码错误");
        }*/

        Users users = userService.login(id, password);
        if (users == null) {
            return Result.error("登录失败");
        }
        if (users.getStatus() == 0) {
            return Result.error("该用户已经被禁用");
        }
        String autoLogin = request.getParameter("autoLogin");
        if (!ObjectUtils.isEmpty(autoLogin)) {
            Cookie cookie = new Cookie("autoLogin", users.getId() + "&" + users.getPwd());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        session.setAttribute("user", users);
        Integer roleId = users.getRoleId();
        List<Integer> authIds = roleService.selectByRoleId(roleId);
        List<Auth> authList = new ArrayList<>();
        for (Integer authId : authIds) {
            Auth auth=authService.selectById(authId);
            authList.add(auth);
        }
        session.setAttribute("auths", authList);
        return Result.ok("登录成功");
    }


    @RequestMapping("/logout")
    public Result logout(HttpSession session , HttpServletRequest request , HttpServletResponse response) {
        //session.invalidate();
        session.removeAttribute("user");
        session.removeAttribute("auths");

        Cookie cookie = CookieUtil.getCookie(request, "autoLogin");
        //设置Cookie存活时间为0
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return Result.ok("退出成功");
    }



}
