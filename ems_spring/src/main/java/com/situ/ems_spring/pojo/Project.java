package com.situ.ems_spring.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Project {
    @ExcelProperty(value = "ID")
    private Integer id;
    @ExcelProperty(value = "name")
    private String name;
    @ExcelProperty(value = "项目进展")
    private String projectProgress;
    @ExcelProperty(value = "项目规模")
    private Integer projectSize;
    @ExcelProperty(value = "项目起始时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @ExcelProperty(value = "项目的完成时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @ExcelProperty(value = "项目组ID")
    private Integer projectTeamId;
    @ExcelProperty(value = "项目状态")
    private Integer state;
}
