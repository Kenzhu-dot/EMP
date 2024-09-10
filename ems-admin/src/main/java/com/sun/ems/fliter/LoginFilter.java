package com.sun.ems.fliter;


import com.sun.ems.pojo.Auth;
import com.sun.ems.pojo.Users;
import com.sun.ems.service.IauthService;
import com.sun.ems.service.IroleService;
import com.sun.ems.service.IusersService;
import com.sun.ems.service.impl.AuthServiceImpl;
import com.sun.ems.service.impl.RoleServiceImpl;
import com.sun.ems.service.impl.UsersServiceImpl;
import com.sun.ems.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String method = request.getParameter("method");
        if(uri.startsWith("/static")||uri.equals("/verifyCode")|| uri.equals("/page/login")|| uri.equals("/users")&&"login".equals(method)){
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            Cookie cookie = CookieUtil.getCookie(request, "autoLogin");
            if (cookie != null) {
                String[] values = cookie.getValue().split("&");
                String id = values[0];
                String password = values[1];
                IusersService usersService = new UsersServiceImpl();
                Users user = usersService.login(Integer.parseInt(id), password);
                if (user != null) {
                    session.setAttribute("user", user);
                    Integer roleId = user.getRoleId();
                    IroleService roleService = new RoleServiceImpl();
                    List<Integer> authIds = roleService.selectByRoleId(roleId);
                    IauthService authService = new AuthServiceImpl();
                    List<Auth> authList = new ArrayList<>();
                    for (Integer authId : authIds) {
                        Auth auth=authService.selectById(authId);
                        authList.add(auth);
                    }
                    session.setAttribute("auths", authList);
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }


            response.sendRedirect("/page/login");
            return ;
        }
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
