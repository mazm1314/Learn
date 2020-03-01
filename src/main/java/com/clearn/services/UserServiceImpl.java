package com.clearn.services;

import com.clearn.bean.User;
import com.clearn.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Administrator
 * @Date 2018.0:06
 */
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserByUserNameAndUserPass(String userName, String userPass) {
        return userRepository.getByUserNameAndUserPass(userName,userPass);
    }

    /**
     * 根据用户ID获得用户；
     * @param userId
     * @return
     */
    @Override
    public User getUserById(String userId) {
        return userRepository.findOne(Long.parseLong(userId));
    }

    /**
     * 更新用户信息；
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
