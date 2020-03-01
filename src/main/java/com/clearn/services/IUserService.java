package com.clearn.services;

import com.clearn.bean.User;

/**
 * @Author Administrator
 * @Date 2018.0:06
 */
public interface IUserService {

    User getUserByUserNameAndUserPass(String userName, String userPass);

    User getUserById(String userId);

    void updateUser(User user);

    void saveUser(User user);
}
