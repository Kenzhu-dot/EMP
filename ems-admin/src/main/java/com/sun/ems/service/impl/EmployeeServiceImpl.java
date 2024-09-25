package com.sun.ems.service.impl;

import com.sun.ems.dao.IemployeeDao;
import com.sun.ems.dao.IusersDao;
import com.sun.ems.dao.impl.EmployeeDaoImpl;
import com.sun.ems.dao.impl.UsersDaoImpl;
import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.Users;
import com.sun.ems.pojo.VO.NVO;
import com.sun.ems.pojo.page.PageResult;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.service.IEmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {
    IemployeeDao employeeDao = new EmployeeDaoImpl();
    IusersDao usersDao = new UsersDaoImpl();
    @Override
    public PageResult selectByPage(XPage<Employee> employeePage , Integer id ,String pwd) {
        System.out.println("Service:  "+employeePage);
        Users user = usersDao.login(id, pwd);
        List<Employee> list = employeeDao.selectByPage(employeePage,user.getRoleId());
        Integer countByPage = employeeDao.selectCountByPage(employeePage,user.getRoleId());
        PageResult pageResult = new PageResult();
        pageResult.setCount(countByPage);
        pageResult.setData(list);
        pageResult.setCode(0);
        pageResult.setMsg("");
        return pageResult;
    }

    @Override
    public void add(Employee employee) {
        employeeDao.add(employee);
    }

    @Override
    public Employee selectById(int id) {
        return employeeDao.selectById(id);
    }

    @Override
    public void edit(Employee employee) {
        employeeDao.edit(employee);
    }

    @Override
    public void deleteById(String id) {
        System.out.println("EmployeeServiceImpl.deleteById");
        employeeDao.deleteById(Integer.parseInt(id));
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            employeeDao.deleteById(Integer.parseInt(id));
        }
    }

    @Override
    public List<NVO> selectNumber() {
        return employeeDao.selectNumber();
    }

    @Override
    public void updateStatus(String id, String status) {
        employeeDao.updateStatus(Integer.parseInt(id),Integer.parseInt(status));
    }
}
