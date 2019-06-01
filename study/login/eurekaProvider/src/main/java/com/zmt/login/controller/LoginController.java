package com.zmt.login.controller;

import com.zmt.login.entity.User;
import com.zmt.login.service.UserService;
import com.zmt.login.utils.MD5;
import com.zmt.login.utils.Ognl;
import com.zmt.login.utils.Rules;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @CLASSNAME :LoginController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/29  13:44
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class LoginController {
    Log log = LogFactory.getLog(TestController.class);

    @Autowired
    UserService userService;

    /*查询用户信息*/
    @RequestMapping(value = "/loginForm",method = RequestMethod.POST)
    public String Login(@RequestParam(name = "userName")String userName, @RequestParam(name = "userPwd")String userPwd,
                        HttpServletRequest request)throws Exception{
            if(Ognl.isEmpty(userName)){//账户不能为空
                return Rules.RULES_FIRST;
            }else if(Ognl.isEmpty(userPwd)){//密码不能为空
                return Rules.RULES_SECOND;
            }else{
                User u = userService.selectByAccount(userName);
                if(Ognl.isEmpty(u)){//查询不到结果
                    return Rules.RULES_THIRD;
                }else{//用户存在
                    if(MD5.verify(userPwd,Rules.RULES_SALT,u.getPassword())){
                        request.getSession().setAttribute("u_login_allow",u);
                        request.getSession().setMaxInactiveInterval(60);//session过期时间 单位：s
                        log.info("登陆成功！");
                        return Rules.RULES_OK;//正常登陆
                    }else{
                        return Rules.RULES_FORTH;
                    }
                }
            }
    }

    /*用户注册*/
    @RequestMapping(value = "/insertForm",method = RequestMethod.POST)
    public String Login(@RequestParam(name = "userName")String userName, @RequestParam(name = "userPwd")String userPwd,
                        @RequestParam(name = "userTel")String userTel, @RequestParam(name = "userEmail")String userEmail,
                        HttpServletRequest request)throws Exception{
        if(Ognl.isEmpty(userName)){//账户不能为空
            return Rules.RULES_FIRST;
        }else if(Ognl.isEmpty(userPwd)){//密码不能为空
            return Rules.RULES_SECOND;
        }else if(Ognl.isEmpty(userTel)){//电话不能为空
            return Rules.RULES_FIVE;
        }else if(Ognl.isEmpty(userEmail)){//邮箱不能为空
            return Rules.RULES_SIX;
        }else{
            /*查询账号是否存在*/
            User u = userService.selectByAccount(userName);
            if(Ognl.isNotEmpty(u)){//账户已存在
                return Rules.RULES_SEVEN;
            }else{
                int m = userService.addUser(25,userName,userName,MD5.md5(userPwd,Rules.RULES_SALT),userTel,userEmail);
                if(m > 0){
                    User user = userService.selectByAccount(userName);
                    request.getSession().setAttribute("u_login_allow",user);
                    request.getSession().setMaxInactiveInterval(30*60);//session过期时间 单位：s
                    return Rules.RULES_OK;//注册成功
                }else{
                    return Rules.RULES_ERROR;//注册失败
                }
            }
        }

    }

}
