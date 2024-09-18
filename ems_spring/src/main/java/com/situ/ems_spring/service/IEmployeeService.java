package com.situ.ems_spring.service;


import com.situ.ems_spring.pojo.Employee;
import com.situ.ems_spring.pojo.Page.EmployeePage;
import com.situ.ems_spring.pojo.PageResult;

public interface IEmployeeService {
    void updateStatus(String id, String status);

    PageResult selectByPage(EmployeePage employeePage, Integer roleId);

    void add(Employee employee);

    void deleteById(String id);

    void deleteAll(String[] ids);
}
