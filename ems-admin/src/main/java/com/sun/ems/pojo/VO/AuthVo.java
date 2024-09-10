package com.sun.ems.pojo.VO;

import com.sun.ems.pojo.Auth;
import lombok.Data;


@Data
public class AuthVo {
    private Auth auth;
    private int state;
}
