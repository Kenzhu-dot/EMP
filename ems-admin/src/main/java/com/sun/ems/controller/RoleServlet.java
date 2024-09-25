package com.sun.ems.controller;

import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Role;
import com.sun.ems.pojo.Users;
import com.sun.ems.service.IRoleService;
import com.sun.ems.service.impl.RoleServiceImpl;
import com.sun.ems.util.JSONUtil;
import com.sun.ems.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    IRoleService roleService = new RoleServiceImpl();

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
            case "selectById":
                selectById(req,resp);
                break;
            case "LTById":
                LTById(req,resp);
                break;
            case "selectAll":
                selectAll(req,resp);
                break;
            case "selectForAuth":
                selectForAuth(req,resp);
                break;
            case "updateMidTable":
                updateMidTable(req,resp);
                break;
        }
    }

    private void updateMidTable(HttpServletRequest req, HttpServletResponse resp) {
        String roleId = req.getParameter("roleId");
        String authId = req.getParameter("authId");
        String state = req.getParameter("state");
        roleService.updateMidTable(roleId,authId,state);
        JSONUtil.toJSON(resp, Result.ok("修改成功"));
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        List<Role>roles = roleService.LTById(user.getRoleId());
        JSONUtil.toJSON(resp,Result.ok(roles));
    }

    private void LTById(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        List<Role> roles = roleService.LTById(user.getRoleId());
        JSONUtil.toJSON(resp, Result.ok(roles));
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void selectForAuth(HttpServletRequest req, HttpServletResponse resp) {
        String roleId = req.getParameter("roleId");
        List<Auth>authList = roleService.selectForAuth(roleId);
        JSONUtil.toJSON(resp,Result.ok(authList));
    }
}
