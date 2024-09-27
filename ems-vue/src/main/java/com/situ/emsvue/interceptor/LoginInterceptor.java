package com.situ.emsvue.interceptor;



import com.situ.emsvue.util.JwtUtil;
import com.situ.emsvue.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try{
            Map<String, Object> map = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(map);
            return true;
        }catch (RuntimeException e){
            response.setStatus(401);
            return false;
        }
    }
}
