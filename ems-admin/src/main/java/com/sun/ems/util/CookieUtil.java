package com.sun.ems.util;

import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
