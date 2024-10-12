package com.situ.emsvue.service;

import com.situ.emsvue.pojo.entity.Team;

import java.util.List;
import java.util.Map;

public interface ITeamService {
    List<Team> selectAll();

    void add(Team team);

    void deletedById(Integer id);

    void deleteAll(Integer[] ids);

    Map<String, Object> detailById(Integer id);
}
