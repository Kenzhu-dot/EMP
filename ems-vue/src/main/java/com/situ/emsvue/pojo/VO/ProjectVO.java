package com.situ.emsvue.pojo.VO;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProjectVO implements Serializable {
    @ExcelProperty(value = "ID")
    private Integer id;
    @ExcelProperty(value = "Name")
    private String name;
    @ExcelProperty(value = "Progress")
    private String projectProgress;
    @ExcelProperty(value = "Size")
    private  Integer projectSize;
    @ExcelProperty(value = "CreateTime")
    private Date createTime;
    @ExcelProperty(value = "EndTime")
    private Date endTime;
    @ExcelProperty(value = "TeamId")
    private Integer projectTeamId;
    @ExcelProperty("TeamName")
    private String teamName;
    @ExcelProperty("Status")
    private Integer status;
}
