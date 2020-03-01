package com.clearn.bean;

import javax.persistence.*;

/**
 * @Author Administrator
 * @Date 2018.22:21
 * @Description 用户
 */
@Entity
@Table(name = "user")
public class User {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long userId;    // 用户ID
    private String userName;    // 用户名；
    private String userPass;    // 用户密码；

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
