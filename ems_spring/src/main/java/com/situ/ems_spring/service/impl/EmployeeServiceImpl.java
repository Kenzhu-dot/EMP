package com.situ.ems_spring.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.ems_spring.mapper.EmployeeMapper;
import com.situ.ems_spring.mapper.UserMapper;
import com.situ.ems_spring.pojo.Employee;
import com.situ.ems_spring.pojo.Page.EmployeePage;
import com.situ.ems_spring.pojo.PageResult;
import com.situ.ems_spring.pojo.Users;
import com.situ.ems_spring.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void updateStatus(String id, String status) {
        employeeMapper.updateStatus(Integer.parseInt(id) , Integer.parseInt(status));
    }

    @Override
    public PageResult selectByPage(EmployeePage employeePage,Integer roleId) {
        PageHelper.startPage(employeePage.getPage(),employeePage.getLimit());
        List<Employee> list = employeeMapper.selectByPage(employeePage ,roleId);
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        int count = (int) pageInfo.getTotal();
        PageResult pageResult = new PageResult();
        pageResult.setCode(0);
        pageResult.setCount(count);
        pageResult.setData(list);
        return pageResult;
    }

    @Override
    public void add(Employee employee) {
        employeeMapper.add(employee);
    }

    @Override
    public void deleteById(String id) {
        employeeMapper.deleteById(Integer.parseInt(id));
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            employeeMapper.deleteById(Integer.parseInt(id));
        }
    }
}
