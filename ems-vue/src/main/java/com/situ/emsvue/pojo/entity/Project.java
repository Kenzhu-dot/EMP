package com.situ.emsvue.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Project {private Integer id;
    private String name;
    private String projectProgress;
    private  Integer projectSize;
    private Date createTime;
    private Date endTime;
    private Integer projectTeamId;
    private String teamName;
    private Integer state;
}
