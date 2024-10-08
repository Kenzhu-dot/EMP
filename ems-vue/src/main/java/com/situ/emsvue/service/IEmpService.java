package com.situ.emsvue.service;

import com.situ.emsvue.pojo.entity.Emp;
import com.situ.emsvue.pojo.Query.EmpQuery;

import java.util.Map;

public interface IEmpService {
    Map<String,Object> list(EmpQuery empQuery,Integer roleId);

    Emp selectById(Integer id);

    void deleteById(Integer id);

    void add(Emp emp);

    void deleteAll(Integer[] ids);

    void edit(Emp emp);

    void updateStatus(Integer id, Integer status);
}
