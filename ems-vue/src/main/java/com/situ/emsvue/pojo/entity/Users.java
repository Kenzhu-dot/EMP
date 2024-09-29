package com.situ.emsvue.pojo.entity;

import lombok.Data;

@Data
public class Users {
    private Integer id;
    private String pwd;
    private Integer roleId;
    private Integer status;
    private Integer deleted;
    private String image;
}
