package com.situ.emsvue.pojo.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class Auth {
    private Integer id;
    private String name;
    private String path;
    private Integer status;
    private String icon;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Auth auth = (Auth) object;
        return Objects.equals(id, auth.id);
    }
}
