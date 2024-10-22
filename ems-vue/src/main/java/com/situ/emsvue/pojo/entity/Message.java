package com.situ.emsvue.pojo.entity;

import lombok.Data;

@Data
public class Message {
    private Integer emId;
    private Integer leaderId;
    private String title;
    private String message;
    private String image;
}
