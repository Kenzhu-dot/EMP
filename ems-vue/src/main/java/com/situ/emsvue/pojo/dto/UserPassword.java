package com.situ.emsvue.pojo.dto;

import lombok.Data;

@Data
public class UserPassword {
    private String oldPassword;
    private String newPassword;
}
