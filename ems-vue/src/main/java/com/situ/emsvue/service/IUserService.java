package com.situ.emsvue.service;

import com.situ.emsvue.pojo.Auth;
import com.situ.emsvue.pojo.Info.LoginInfo;
import com.situ.emsvue.pojo.Users;

import java.util.List;

public interface IUserService {
    Users login(LoginInfo loginInfo);

    List<Auth> userInform(Integer roleId);

    Users selectOldPassword(Integer id, String oldPassword);

    void updatePassword(Integer id, String newPassword);
}
