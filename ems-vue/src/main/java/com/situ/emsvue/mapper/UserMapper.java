package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.Info.LoginInfo;
import com.situ.emsvue.pojo.Users;

public interface UserMapper {
    Users login(LoginInfo loginInfo);

    Users selectOldPassword(Integer id, String oldPassword);

    void updatePassword(Integer id, String newPassword);
}
