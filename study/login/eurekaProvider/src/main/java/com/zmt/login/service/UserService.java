package com.zmt.login.service;

import com.zmt.login.entity.User;

/**
 * Created by Administrator on 2019/3/29.
 */
public interface UserService {
    /*查询用户信息*/
    public User selectByAccount(String account);
    /*添加用户信息*/
    public int addUser(int age,String name,String account,String password,String tel,String email);
}
