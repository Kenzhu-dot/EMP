package com.situ.ems_spring.pojo;

import lombok.Data;

@Data
public class Employee {
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
}
