package com.clearn.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Administrator
 * @Date 2018.19:37
 */
public class UserHelp {

    // 从session中获得用户名称；
    public static String getUserName(HttpServletRequest request){
        String sessionUser = (String) request.getSession().getAttribute("sessionUser");
        return sessionUser.split("_")[0];
    }
    // 从session中获得用户ID;
    public static Long getUserId(HttpServletRequest request){
        String sessionUser = (String) request.getSession().getAttribute("sessionUser");
        return Long.parseLong(sessionUser.split("_")[1]);
    }

}
