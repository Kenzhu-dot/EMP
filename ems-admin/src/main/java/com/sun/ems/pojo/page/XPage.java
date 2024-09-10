package com.sun.ems.pojo.page;

import lombok.Data;

@Data
public class XPage<T>{
    private int page;
    private int limit;
    private T X;
}
