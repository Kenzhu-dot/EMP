package com.situ.emsvue.service;

import com.situ.emsvue.pojo.entity.Auth;
import com.situ.emsvue.pojo.Info.LoginInfo;
import com.situ.emsvue.pojo.entity.Users;
import com.situ.emsvue.pojo.VO.UserInfoVO;

import java.util.List;

public interface IUserService {
    Users login(LoginInfo loginInfo);

    List<Auth> userInform(Integer roleId);

    Users selectOldPassword(Integer id, String oldPassword);

    void updatePassword(Integer id, String newPassword);

    UserInfoVO allInfo(Integer id);

    void edit(UserInfoVO userInfoVO);
}
