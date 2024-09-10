package com.sun.ems.pojo.page;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T>{
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}
