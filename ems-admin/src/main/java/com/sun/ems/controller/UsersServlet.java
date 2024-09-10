package com.sun.ems.controller;


import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IauthService;
import com.sun.ems.service.IroleService;
import com.sun.ems.service.IusersService;
import com.sun.ems.service.impl.AuthServiceImpl;
import com.sun.ems.service.impl.RoleServiceImpl;
import com.sun.ems.service.impl.UsersServiceImpl;
import com.sun.ems.util.CookieUtil;
import com.sun.ems.util.JSONUtil;
import com.sun.ems.util.MyBeanUtil;
import com.sun.ems.util.Result;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    IusersService usersService = new UsersServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null || "".equals(method)) {
            method="selectByPage";
        }
        switch (method) {
            case "selectByPage":
                selectByPage(req,resp);
                break;
            case "login":
                login(req,resp);
                break;
            case "register":
                register(req,resp);
                break;
            case "deleteById":
                deleteById(req,resp);
                break;
            case "deleteAll":
                deleteAll(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "add":
                add(req,resp);
                break;
            case "logout" :
                logout(req,resp);
                break;
        }
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        Users user = MyBeanUtil.copyToBean(req, Users.class);
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        XPage<Users> usersPage=new XPage<>();
        usersPage.setPage(Integer.parseInt(page));
        usersPage.setLimit(Integer.parseInt(limit));
        usersPage.setX(user);
        PageResult result = usersService.selectByPage(usersPage);
        JSONUtil.toJSON(resp, result);
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        //session.invalidate();
        session.removeAttribute("user");
        session.removeAttribute("auths");

        Cookie cookie = CookieUtil.getCookie(req, "autoLogin");
        //设置Cookie存活时间为0
        if (cookie != null) {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        /*JSONUtil.toJSON(resp, Result.ok("退出成功"));*/
        try {
            resp.sendRedirect("/page/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("AdminServlet.login");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String codeInSession = (String) session.getAttribute("codeInSession");
        if (!codeInSession.equalsIgnoreCase(code)) {
            JSONUtil.toJSON(resp, Result.error("验证码错误"));
            return;
        }

        Users users = usersService.login(Integer.parseInt(id), password);
        System.out.println("users:"+users);
        if (users == null) {
            JSONUtil.toJSON(resp, Result.error("登录失败"));
            return;
        }
        if (users.getStatus() == 0) {
            JSONUtil.toJSON(resp, Result.error("该用户已经被禁用"));
            return;
        }
        String autoLogin = req.getParameter("autoLogin");
        if (!ObjectUtils.isEmpty(autoLogin)) {
            Cookie cookie = new Cookie("autoLogin", users.getId() + "&" + users.getPwd());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath(req.getContextPath());
            resp.addCookie(cookie);
        }
        session.setAttribute("user", users);
        Integer roleId = users.getRoleId();
        IroleService roleService = new RoleServiceImpl();
        List<Integer> authIds = roleService.selectByRoleId(roleId);

        IauthService authService = new AuthServiceImpl();
        List<Auth> authList = new ArrayList<>();
        for (Integer authId : authIds) {
            Auth auth=authService.selectById(authId);
            authList.add(auth);
        }
        session.setAttribute("auths", authList);
        JSONUtil.toJSON(resp, Result.ok("登录成功"));
    }
}
