package com.situ.emsvue.service.impl;

import com.situ.emsvue.mapper.TeamMapper;
import com.situ.emsvue.pojo.entity.Team;
import com.situ.emsvue.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Team> selectAll() {
        teamMapper.selectNumber();
        return teamMapper.selectAll();
    }

    @Override
    public void add(Team team) {
        teamMapper.add(team);
    }

    @Override
    public void deletedById(Integer id) {
        teamMapper.deletedById(id);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            teamMapper.deletedById(id);
        }
    }

    @Override
    public Map<String, Object> detailById(Integer id) {
        List<String> empName = teamMapper.selectENByTeamId(id);
        List<String> projectName = teamMapper.selectPNByTeamId(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("empName", empName);
        map.put("projectName", projectName);
        return map;
    }
}
