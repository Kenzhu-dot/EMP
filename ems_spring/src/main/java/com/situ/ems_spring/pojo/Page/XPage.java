package com.situ.ems_spring.pojo.Page;

import lombok.Data;

@Data
public class XPage<T>{
    private int page;
    private int limit;
    private T X;
}
