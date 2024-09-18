package com.situ.ems_spring.controller;

import com.situ.ems_spring.pojo.Employee;
import com.situ.ems_spring.pojo.Page.EmployeePage;
import com.situ.ems_spring.pojo.Page.XPage;
import com.situ.ems_spring.pojo.PageResult;
import com.situ.ems_spring.pojo.Users;
import com.situ.ems_spring.service.IEmployeeService;
import com.situ.ems_spring.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @RequestMapping("/updateStatus")
    public Result updateStatus(HttpServletRequest req){
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        employeeService.updateStatus(id,status);
        return Result.ok("更新成功");
    }

    @RequestMapping("/selectByPage")
    public PageResult selectByPage(HttpSession session , EmployeePage employeePage){
        if ("".equals(employeePage.getName())){
            employeePage.setName(null);
        }
        if ("0".equals(employeePage.getGender())){
            employeePage.setGender(null);
        }
        Users user = (Users) session.getAttribute("user");
        System.out.println(user);
        Integer roleId = user.getRoleId();
        System.out.println("employee"+employeePage);

        PageResult result = employeeService.selectByPage(employeePage,roleId);
        return result;
    }

    @RequestMapping("/add")
    public Result add(Employee employee){
        employeeService.add(employee);
        return Result.ok("添加成功");
    }

    @RequestMapping("/deleteById")
    public Result deleteById(String id){
        employeeService.deleteById(id);
        return Result.ok("删除成功");
    }

    @RequestMapping("/deleteAll")
    public Result deleteAll(String[] ids ){
        employeeService.deleteAll(ids);
        return Result.ok("删除成功");
    }




}
