package com.sun.ems.dao;

import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.VO.NVO;
import com.sun.ems.pojo.page.XPage;

import java.util.List;

public interface IemployeeDao {
    List<Employee> selectByPage(XPage<Employee> employeePage , Integer roleId);

    Integer selectCountByPage(XPage<Employee> employeePage , Integer roleId);

    void add(Employee employee);

    Employee selectById(int id);

    void edit(Employee employee);

    void deleteById(int id);

    List<NVO> selectNumber();

    void updateStatus(int id, Integer status);
}
