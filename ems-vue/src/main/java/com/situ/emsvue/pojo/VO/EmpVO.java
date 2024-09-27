package com.situ.emsvue.pojo.VO;

import lombok.Data;

@Data
public class EmpVO {
    private Integer id;
    private String name;
    private String gender;
    private Double salary;
    private Integer roleId;
    private String roleName;
    private Integer leaderId;
    private Integer lateRecord;
    private Integer absenteeismRecord;
    private Integer leaveRecord;
    private Integer status;
    private Integer deleted;
    private String leaderName;
}
