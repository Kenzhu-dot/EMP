package com.situ.ems_spring.pojo.VO;


import com.situ.ems_spring.pojo.Auth;
import lombok.Data;


@Data
public class AuthVo {
    private Auth auth;
    private int state;
}
