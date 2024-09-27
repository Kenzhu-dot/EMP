package com.situ.emsvue.pojo.Info;

import lombok.Data;

@Data
public class LoginInfo {
    private Integer id;
    private String pwd;
    private Integer roleId;
    private Integer status;
    private Integer deleted;
    private String captcha;
}
