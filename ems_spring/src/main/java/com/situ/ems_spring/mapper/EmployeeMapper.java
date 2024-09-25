package com.situ.ems_spring.mapper;

import com.situ.ems_spring.pojo.Employee;
import com.situ.ems_spring.pojo.Page.EmployeePage;
import com.situ.ems_spring.pojo.VO.NVO;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectByPage(EmployeePage employeePage, Integer roleId);

    void add(Employee employee);

    void deleteById(int id);

    void updateStatus(int id, int status);

    void edit(Employee employee);

    Employee selectById(Integer id);

    List<NVO> selectNumber();
}
