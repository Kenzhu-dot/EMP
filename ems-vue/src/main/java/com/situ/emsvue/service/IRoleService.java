package com.situ.emsvue.service;


import com.situ.emsvue.pojo.entity.Role;
import com.situ.emsvue.pojo.VO.AuthVO;

import java.util.List;

public interface IRoleService {
    List<Role> list();

    List<AuthVO> edit(Integer id);

    void updateMid(Integer id, AuthVO[] authVO);
}
