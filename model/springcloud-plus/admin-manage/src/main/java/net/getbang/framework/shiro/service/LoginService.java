package net.getbang.framework.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.getbang.common.constant.CommonConstant;
import net.getbang.common.constant.ShiroConstants;
import net.getbang.common.constant.UserConstants;
import net.getbang.common.exception.user.CaptchaException;
import net.getbang.common.exception.user.UserBlockedException;
import net.getbang.common.exception.user.UserNotExistsException;
import net.getbang.common.exception.user.UserPasswordNotMatchException;
import net.getbang.common.utils.MessageUtils;
import net.getbang.common.utils.ServletUtils;
import net.getbang.common.utils.SystemLogUtils;
import net.getbang.project.system.user.domain.User;
import net.getbang.project.system.user.service.IUserService;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class LoginService
{
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public User login(String username, String password)
    {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getStrAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userService.selectUserByName(username);

        if (user == null)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (UserConstants.USER_BLOCKED == user.getStatus())
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRefuseDes()));
            throw new UserBlockedException(user.getRefuseDes());
        }
        
        SystemLogUtils.log(username, CommonConstant.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        return user;
    }

}
