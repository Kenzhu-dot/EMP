package com.sun.ems.pojo.page;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectPage {
    @ExcelProperty(value = "ID")
    private Integer id;
    @ExcelProperty(value = "项目名")
    private String name;
    @ExcelProperty(value = "项目进度")
    private String projectProgress;
    @ExcelProperty(value = "项目规模")
    private  Integer projectSize;
    @ExcelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @ExcelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @ExcelProperty(value = "负责项目组号")
    private Integer projectTeamId;
    @ExcelProperty(value = "负责项目组名")
    private String teamName;
    @ExcelProperty(value = "项目状态")
    private Integer state;
}
