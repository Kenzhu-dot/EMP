package com.situ.ems_spring.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ObjectUtils;



public class CookieUtil {
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie c : cookies) {
                String name = c.getName();
                if (cookieName.equals(name)) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie;
    }
}
