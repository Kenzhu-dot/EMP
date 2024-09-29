package com.situ.emsvue.service.impl;

import com.situ.emsvue.mapper.TeamMapper;
import com.situ.emsvue.pojo.entity.Team;
import com.situ.emsvue.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Team> selectAll() {
        return teamMapper.selectAll();
    }
}
