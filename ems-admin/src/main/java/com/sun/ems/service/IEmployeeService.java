package com.sun.ems.service;

import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.VO.NVO;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;

import java.util.List;

public interface IEmployeeService {
    PageResult selectByPage(XPage<Employee> employeePage ,Integer id ,String pwd);

    void add(Employee employee);

    Employee selectById(int id);

    void edit(Employee employee);

    void deleteById(String id);

    void deleteAll(String[] ids);

    List<NVO> selectNumber();

    void updateStatus(String id, String status);
}
