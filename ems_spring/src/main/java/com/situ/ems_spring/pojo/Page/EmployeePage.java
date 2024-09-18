package com.situ.ems_spring.pojo.Page;

import lombok.Data;

@Data
public class EmployeePage {
    private Integer id;
    private String name;
    private String gender;
    private double salary;
    private Integer roleId;
    private Integer lateRecord;
    private Integer absenteeismRecord;
    private Integer leaveRecord;
    private Integer leaderId;
    private Integer state;
    private String leaderName;
    private String roleName;
    private Integer page;
    private Integer limit;
}
