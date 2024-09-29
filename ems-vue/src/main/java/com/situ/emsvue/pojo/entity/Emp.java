package com.situ.emsvue.pojo.entity;

import lombok.Data;

@Data
public class Emp {
    private Integer id;
    private String name;
    private String gender;
    private Double salary;
    private Integer roleId;
    private Integer leaderId;
    private Integer lateRecord;
    private Integer absenteeismRecord;
    private Integer leaveRecord;
    private Integer status;
    private Integer deleted;
}
