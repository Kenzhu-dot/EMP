package com.sun.ems.controller;

import com.sun.ems.dao.IemployeeDao;
import com.sun.ems.dao.impl.EmployeeDaoImpl;
import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.Role;
import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.VO.NVO;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IemployeeService;
import com.sun.ems.service.IroleService;
import com.sun.ems.service.impl.EmployeeServiceImpl;
import com.sun.ems.service.impl.RoleServiceImpl;
import com.sun.ems.util.JSONUtil;
import com.sun.ems.util.MyBeanUtil;
import com.sun.ems.util.Result;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    IemployeeService employeeService = new EmployeeServiceImpl();
    IemployeeDao employeeDao = new EmployeeDaoImpl();
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
            case "selectNumber":
                selectNumber(req,resp);
                break;
            case "updateStatus":
                updateStatus(req,resp);
                break;
        }
    }

    private void updateStatus(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        System.out.println("id:"+id+",status:"+status);
        employeeService.updateStatus(id,status);
        JSONUtil.toJSON(resp,"更新成功");
    }

    private void selectNumber(HttpServletRequest req, HttpServletResponse resp) {
        List<NVO> numbs =  employeeService.selectNumber();
        JSONUtil.toJSON(resp,numbs);
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        IroleService roleService = new RoleServiceImpl();
        List<Role> roles = roleService.LTById(user.getRoleId());
        Employee employee = employeeService.selectById(Integer.parseInt(id));
        Map<String,Object> map = new HashMap();
       map.put("employee",employee);
       map.put("roles",roles);
       JSONUtil.toJSON(resp,Result.ok(map));
    }


    private void add(HttpServletRequest req, HttpServletResponse resp) {
        Employee employee = MyBeanUtil.copyToBean(req, Employee.class);
        employeeService.add(employee);
        JSONUtil.toJSON(resp, Result.ok("添加成功"));
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {
        Employee employee = MyBeanUtil.copyToBean(req, Employee.class);
        employeeService.edit(employee);
        JSONUtil.toJSON(resp,Result.ok("编辑成功"));
    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
        String[] ids = req.getParameterValues("ids[]");
        employeeService.deleteAll(ids);
        JSONUtil.toJSON(resp,Result.ok("删除成功"));
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        employeeService.deleteById(id);
        JSONUtil.toJSON(resp,Result.ok("删除成功"));
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        Employee employee = MyBeanUtil.copyToBean(req, Employee.class);
        if ("".equals(employee.getName())){
            employee.setName(null);
        }
        if ("0".equals(employee.getGender())){
            employee.setGender(null);
        }
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        System.out.println(user);
        Integer id = user.getId();
        String pwd = user.getPwd();
        System.out.println("employee"+employee);
        XPage<Employee> employeePage = MyBeanUtil.copyToBean(req, XPage.class);
        employeePage.setX(employee);
        System.out.println("Servlet:  "+employeePage);
        PageResult result = employeeService.selectByPage(employeePage,id,pwd);
        JSONUtil.toJSON(resp, result);
    }
}
