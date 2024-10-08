package com.situ.emsvue.service.impl;

import com.situ.emsvue.mapper.AuthMapper;
import com.situ.emsvue.pojo.VO.AuthVO;
import com.situ.emsvue.pojo.entity.Auth;
import com.situ.emsvue.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private AuthMapper authMapper;
    @Override
    public List<AuthVO> list() {
        return authMapper.list();
    }

    @Override
    public void add(Auth auth) {
        authMapper.add(auth);
    }

    @Override
    public void deleteById(Integer id) {
        authMapper.deleteById(id);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        for (Integer id : ids) {
            authMapper.deleteById(id);
        }
    }

    @Override
    public Auth selectById(Integer id) {
        return authMapper.selectById(id);
    }

    @Override
    public void edit(Auth auth) {
        authMapper.edit(auth);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        authMapper.updateStatus(id , status);
    }
}
