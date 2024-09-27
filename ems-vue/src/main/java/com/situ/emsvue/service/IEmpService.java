package com.situ.emsvue.service;

import com.situ.emsvue.pojo.Emp;
import com.situ.emsvue.pojo.Query.EmpQuery;

import java.util.List;
import java.util.Map;

public interface IEmpService {
    Map<String,Object> list(EmpQuery empQuery,Integer roleId);

    Emp selectById(Integer id);

    void deleteById(Integer id);

    void add(Emp emp);

    void deleteAll(Integer[] ids);
}
