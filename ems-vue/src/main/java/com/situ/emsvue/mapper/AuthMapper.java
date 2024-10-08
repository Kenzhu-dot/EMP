package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.VO.AuthVO;
import com.situ.emsvue.pojo.entity.Auth;

import java.util.List;

public interface AuthMapper {
    List<AuthVO> list();

    void add(Auth auth);

    void deleteById(Integer id);

    Auth selectById(Integer id);

    void edit(Auth auth);

    void updateStatus(Integer id, Integer status);
}
