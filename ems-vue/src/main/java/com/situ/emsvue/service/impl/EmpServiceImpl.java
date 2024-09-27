package com.situ.emsvue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.emsvue.mapper.EmpMapper;
import com.situ.emsvue.mapper.RoleMapper;
import com.situ.emsvue.pojo.Emp;
import com.situ.emsvue.pojo.Query.EmpQuery;
import com.situ.emsvue.pojo.VO.EmpVO;
import com.situ.emsvue.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements IEmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Map<String,Object> list(EmpQuery empQuery  , Integer roleId) {
        PageHelper.startPage(empQuery.getPage(),empQuery.getLimit());
         List<EmpVO> emps  = empMapper.list(empQuery,roleId);
        PageInfo<EmpVO> pageInfo = new PageInfo<>(emps);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("list",emps);
        return map;
    }

    @Override
    public Emp selectById(Integer id) {
       return empMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        empMapper.deleteById(id);
    }

    @Override
    public void add(Emp emp) {
        empMapper.add(emp);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            empMapper.deleteById(id);
        }
    }

    @Override
    public void edit(Emp emp) {
        empMapper.edit(emp);
    }
}
