package com.situ.emsvue.controller;
import com.situ.emsvue.pojo.Auth;
import com.situ.emsvue.pojo.Emp;
import com.situ.emsvue.pojo.Info.LoginInfo;
import com.situ.emsvue.pojo.Users;
import com.situ.emsvue.pojo.VO.UserInfoVO;
import com.situ.emsvue.pojo.dto.UserPassword;
import com.situ.emsvue.service.IUserService;
import com.situ.emsvue.util.CommonUtil;
import com.situ.emsvue.util.JwtUtil;
import com.situ.emsvue.util.Result;
import com.situ.emsvue.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo loginInfo, HttpServletRequest request) {
        String captcha = (String) redisTemplate.opsForValue().get(CommonUtil.getCaptchaKey(request));
        if (captcha == null) {
            return Result.error("验证码过期");
        }
        if (!captcha.equals(loginInfo.getCaptcha())) {
            return Result.error("验证码错误");
        }
        Users user = userService.login(loginInfo);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("roleId", user.getRoleId());
        String token = JwtUtil.createToken(map);
        return Result.ok("登录成功" , token);
    }

    @GetMapping("/userInform")
    public Result userInform(@RequestHeader(name = "Authorization")String token) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        Integer roleId = (Integer) map.get("roleId");
        List<Auth> auths = userService.userInform(roleId);
        return Result.ok(auths);
    }

    @PutMapping("/resetPassword")
    public Result userPassword( @RequestBody UserPassword userPassword) {
        Map<String , Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Users user = userService.selectOldPassword(id , userPassword.getOldPassword());
        if (user == null){
            return Result.error("Original Password is Wrong");
        }
        userService.updatePassword(id , userPassword.getNewPassword());
        return Result.ok("Success");
    }

    @GetMapping("/allInfo")
    public Result allInfo() {
       Map<String,Object>map  = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        UserInfoVO userInfoVO = userService.allInfo(id);
        return Result.ok(userInfoVO);
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody UserInfoVO userInfoVO) {
        userService.edit(userInfoVO);
        return Result.ok("Success!");
    }

}
