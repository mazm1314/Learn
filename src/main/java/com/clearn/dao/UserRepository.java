package com.clearn.dao;

import com.clearn.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Administrator
 * @Date 2018.0:03
 * @Description user
 */
public interface UserRepository extends JpaRepository<User,Long>{

    User getByUserNameAndUserPass(String userName,String userPass);





}
