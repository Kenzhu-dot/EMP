package com.sun.ems.dao.impl;

import com.sun.ems.dao.IemployeeDao;
import com.sun.ems.pojo.Employee;
import com.sun.ems.pojo.VO.NVO;
import com.sun.ems.pojo.page.XPage;
import com.sun.ems.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements IemployeeDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Employee> selectByPage(XPage<Employee> employeePage,Integer roleId) {
        System.out.println("Dao:  "+employeePage);
        String sql = "select *,(SELECT `name` FROM employee WHERE id=b.leader_id)AS leader_name , (SELECT `name` FROM role WHERE role_id = role.id)AS role_name from employee AS b where 1=1";
        List<Object> pageArray = new ArrayList<>();
        if (employeePage.getX()!=null){
            Employee employee = employeePage.getX();
            if (employee.getId() != null) {
                sql += " and id=?";
                pageArray.add(employee.getId());
            }
            if (employee.getName() != null) {
                sql += " and name like ?";
                pageArray.add("%" + employee.getName() + "%");
            }
            if (employee.getGender()!=null){
                sql += " and gender=?";
                pageArray.add(employee.getGender());
            }
            if (employee.getRoleId() != null) {
                sql += " and role_id=?";
                pageArray.add(employee.getRoleId());
            }
        }
        sql +=" and role_id<? ";
        pageArray.add(roleId);
        sql +=" limit ?,?";
        pageArray.add((employeePage.getPage()-1)*employeePage.getLimit());
        pageArray.add(employeePage.getLimit());
        return template.query(sql,new BeanPropertyRowMapper<>(Employee.class),pageArray.toArray());
    }

    @Override
    public Integer selectCountByPage(XPage<Employee> employeePage , Integer roleId) {
        List<Object> pageArray = new ArrayList<>();
        String sql = "select count(*) from employee where 1=1";
        if (employeePage.getX()!=null){
            Employee employee = employeePage.getX();
            if (employee.getId() != null) {
                sql += " and id=?";
                pageArray.add(employee.getId());
            }
            if (employee.getName() != null) {
                sql += " and name like ?";
                pageArray.add("%" + employee.getName() + "%");
            }
            if (employee.getGender()!=null){
                sql += " and gender=?";
                pageArray.add(employee.getGender());
            }
            if (employee.getRoleId() != null) {
                sql += " and role_id=?";
                pageArray.add(employee.getRoleId());
            }
            sql +=" and role_id < ?";
            pageArray.add(roleId);
        }
        return template.queryForObject(sql,Integer.class,pageArray.toArray());
    }

    @Override
    public void add(Employee employee) {
        String sql = "insert into employee(name,gender,salary,role_id,leader_id) values(?,?,?,?,?)";
        template.update(sql,employee.getName(),employee.getGender(),employee.getSalary(),employee.getRoleId(),employee.getLeaderId());
    }

    @Override
    public Employee selectById(int id) {
        String sql ="SELECT *,(SELECT `name` FROM employee WHERE id=b.leader_id)AS leader_name , (SELECT `name` FROM role WHERE role_id = role.id)AS role_name FROM employee AS b WHERE id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Employee.class),id);
    }

    @Override
    public void edit(Employee employee) {
        String sql = "UPDATE employee SET name=?,gender=?,salary=?,role_id=? WHERE id=?";
        template.update(sql,employee.getName(),employee.getGender(),employee.getSalary(),employee.getRoleId(),employee.getId());
    }

    @Override
    public void deleteById(int id) {
        System.out.println("EmployeeDaoImpl.deleteById");
        String sql = "DELETE FROM employee WHERE id=?";
        template.update(sql,id);
    }

    @Override
    public List<NVO> selectNumber() {
        String sql = "SELECT role.`name`,COUNT(*) AS value FROM employee INNER JOIN role WHERE employee.role_id=role.id GROUP BY role_id";
        return template.query(sql,new BeanPropertyRowMapper<>(NVO.class));
    }

    @Override
    public void updateStatus(int id, Integer status) {
        String sql = "UPDATE employee SET state=? WHERE id=?";
        template.update(sql,status,id);
    }
}
