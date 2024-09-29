package com.situ.emsvue.pojo.Query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectQuery {
    private Integer id;
    private String name;
    private String projectProgress;
    private  Integer projectSize;
    private Date createTime;
    private Date endTime;
    private Integer projectTeamId;
    private String teamName;
    private Integer state;
    private Integer page;
    private Integer limit;
}
