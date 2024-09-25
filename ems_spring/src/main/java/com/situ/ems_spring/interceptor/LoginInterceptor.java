package com.situ.ems_spring.interceptor;

import com.situ.ems_spring.pojo.Users;
import com.situ.ems_spring.service.IUserService;
import com.situ.ems_spring.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {
    private IUserService userService;

    public LoginInterceptor(IUserService userService) {
        this.userService = userService;
    }

    public LoginInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            Cookie cookie = CookieUtil.getCookie(request , "autoLogin");
            if (cookie != null) {
                String[] values = cookie.getValue().split("&");
                String id = values[0];
                String pwd = values[1];
                userService.login(Integer.parseInt(id), pwd);
            }
            else {
                response.sendRedirect("/page/login");
                return false;
            }
        }
        /*if (user != null) {
            session.setAttribute("user", user);
            Integer roleId = user.getRoleId();
            List<Integer> authIds = roleService.selectByRoleId(roleId);
            List<Auth> authList = new ArrayList<>();
            for (Integer authId : authIds) {
                Auth auth=authService.selectById(authId);
                authList.add(auth);
            }
            session.setAttribute("auths", authList);
        }*/
        return true;
    }
}
