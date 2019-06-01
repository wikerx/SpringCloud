package com.zmt.login.service.Impl;

import com.zmt.login.entity.User;
import com.zmt.login.mapper.UserDao;
import com.zmt.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @CLASSNAME :UserServiceImpl
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/29  14:06
 * @Version :V1.0
 * @Status : 编写
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    /*查询用户信息*/
    public User selectByAccount(String account){
        return userDao.selectByAccount(account);
    }
    /*添加用户信息*/
    public int addUser(int age,String name,String account,String password,String tel,String email){
        return userDao.addUser(age, name, account, password, tel, email);
    }
}
