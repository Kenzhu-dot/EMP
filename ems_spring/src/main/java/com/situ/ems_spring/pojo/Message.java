package com.situ.ems_spring.pojo;

import lombok.Data;

@Data
public class Message {
    private Integer id;
    private String image;
    private Integer emId;
    private Integer leaderId;
    private Integer state;
    private String emName;
    private String leaderName;
}
