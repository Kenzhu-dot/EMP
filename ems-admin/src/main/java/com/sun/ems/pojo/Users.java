package com.sun.ems.pojo;

import lombok.Data;

@Data
public class Users {
    private Integer id;
    private String pwd;
    private Integer roleId;
    private Integer status;
}
