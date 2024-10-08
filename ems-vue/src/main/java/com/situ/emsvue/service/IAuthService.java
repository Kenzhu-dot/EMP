package com.situ.emsvue.service;

import com.situ.emsvue.pojo.VO.AuthVO;
import com.situ.emsvue.pojo.entity.Auth;

import java.util.List;

public interface IAuthService {
    List<AuthVO> list();

    void add(Auth auth);

    void deleteById(Integer id);

    void deleteAll(List<Integer> ids);

    Auth selectById(Integer id);

    void edit(Auth auth);

    void updateStatus(Integer id, Integer status);
}
