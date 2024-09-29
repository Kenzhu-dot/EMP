package com.situ.emsvue.service.impl;

import com.situ.emsvue.mapper.AuthMapper;
import com.situ.emsvue.pojo.VO.AuthVO;
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
}
