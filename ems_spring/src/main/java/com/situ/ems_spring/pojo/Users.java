package com.situ.ems_spring.pojo;

import lombok.Data;

@Data
public class Users {
    private Integer id;
    private String pwd;
    private Integer roleId;
    private Integer status;
    private String image="1";
}
