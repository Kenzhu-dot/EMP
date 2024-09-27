package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.Emp;
import com.situ.emsvue.pojo.Query.EmpQuery;
import com.situ.emsvue.pojo.VO.EmpVO;

import java.util.List;

public interface EmpMapper {
    List<EmpVO> list(EmpQuery empQuery,Integer roleId);

    Emp selectById(Integer id);

    void deleteById(Integer id);

    void add(Emp emp);
}
