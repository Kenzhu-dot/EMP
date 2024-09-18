package com.situ.ems_spring.pojo.Page;

import lombok.Data;

@Data
public class LoginInfor {
    private Integer id;
    private String password;
    private Integer status;
    private String code;
    private String autoLogin;
}
