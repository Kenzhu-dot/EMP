package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.entity.Team;

import java.util.List;

public interface TeamMapper {
    List<Team> selectAll();

    void add(Team team);

    void deletedById(Integer id);

    List<String> selectENByTeamId(Integer id);

    List<String> selectPNByTeamId(Integer id);

    void selectNumber();
}
