package com.zmt.login.controller;

import com.zmt.login.entity.User;
import com.zmt.login.service.UserService;
import com.zmt.login.utils.MD5;
import com.zmt.login.utils.Ognl;
import com.zmt.login.utils.Rules;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @CLASSNAME :PageController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/29  13:35
 * @Version :V1.0
 * @Status : 编写
 **/
@Controller
public class PageController {
    Log log = LogFactory.getLog(TestController.class);
    @Autowired
    UserService userService;
    /**
     * 通用跳转
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page,HttpServletRequest request) throws Exception{
        if(page.equals("index") || page.equals("login") ||page.equals("insert")){
            return page;
        }else {
            User u = (User) request.getSession().getAttribute("u_login_allow");
            if (Ognl.isEmpty(u)) {
                log.info("非法请求...已拦截！");
                return "login";
            } else {
                User user = userService.selectByAccount(u.getAccount());
                if (!user.getPassword().equals(u.getPassword())) {
                    log.info("非法请求...已拦截！");
                    return "login";
                } else {
                    return page;
                }
            }
        }
    }


    /*判断当前用的sesion存在否，存在切正确才能进入*/
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String Next(HttpServletRequest request) throws Exception{
        User u = (User) request.getSession().getAttribute("u_login_allow");
        if(Ognl.isEmpty(u)){
            log.info("main - 非法请求...已拦截！");
            return "login";
        }else{
            User user = userService.selectByAccount(u.getAccount());
            if(!user.getPassword().equals(u.getPassword())){
                log.info("main - 非法请求...已拦截！");
                return "login";
            }else{
                return "welcome";
            }
        }
    }
}
