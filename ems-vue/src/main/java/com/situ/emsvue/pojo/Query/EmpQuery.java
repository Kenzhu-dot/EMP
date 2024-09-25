package com.situ.emsvue.pojo.Query;

import lombok.Data;

@Data
public class EmpQuery {
    private Integer page;
    private Integer limit;
    private Integer id;
    private String name;
    private String gender;
    private Double salary;
    private Integer roleId;
    private Integer leaderId;
    private Integer deleted;
}
