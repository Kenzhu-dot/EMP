package com.situ.emsvue.pojo.VO;

import lombok.Data;

@Data
public class AuthVO {
    private Integer id;
    private String name;
    private String path;
    private Integer status;
    private String icon;
    private Integer midStatus = 0;
}
