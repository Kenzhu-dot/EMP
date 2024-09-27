package com.situ.emsvue.service.impl;

import com.situ.emsvue.mapper.AuthMapper;
import com.situ.emsvue.mapper.RoleMapper;
import com.situ.emsvue.pojo.Auth;
import com.situ.emsvue.pojo.Role;
import com.situ.emsvue.pojo.VO.AuthVO;
import com.situ.emsvue.service.IEmpService;
import com.situ.emsvue.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Role> list() {
        return roleMapper.list();
    }

    @Override
    public List<AuthVO> edit(Integer id) {
        List<AuthVO>authVOs =  authMapper.list();
        List<Auth>midAuths= roleMapper.userInform(id);
        for (Auth auth : midAuths) {
            for (AuthVO authVO : authVOs) {
                if (auth.getId() == authVO.getId()) {
                    authVO.setMidStatus(1);
                }
            }
        }
        return authVOs;
    }


}
