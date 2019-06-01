package com.yb.mall.common.api.utils;

import com.yb.mall.common.api.base.enums.YesOrNoEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 密码短信相关工具
 * Created by Frank on 2018-08-15.
 * @author Frank
 */
public class PwdSmsUtil {

    public static String pwdRedisKey = "pwdRedisKey";

    public static String pwdRedisKeyActionModify = "_modify_";

    public static String modifyPwdValidStatusReidsKey = "modifyPwdValidStatusReidsKey";

    public static String getModifyPwdKey(String phone){
        return pwdRedisKey+pwdRedisKeyActionModify+phone;
    }

    public static String getModifyPwdValidStatusReidsKey(String phone){
        return modifyPwdValidStatusReidsKey+phone;
    }

    public static void setModifyPwdValidStatusReidsKey(String phone,String status,StringRedisTemplate stringRedisTemplate){
        stringRedisTemplate.opsForValue().set(getModifyPwdValidStatusReidsKey(phone),status);
    }

    /**
     * 设置修改密码-短信的验证码缓存
     * @param phone
     * @param code
     * @param stringRedisTemplate
     */
    public static void setModifyPwdSmsCodeRedis(String phone,String code,StringRedisTemplate stringRedisTemplate){
        stringRedisTemplate.opsForValue().set(getModifyPwdKey(phone),code);
    }


    /**
     * 验证修改密码 短信的验证码
     * @param phone
     * @param code
     * @param stringRedisTemplate
     * @return
     */
    public static boolean validModifyPwdSmsCodeRedis(String phone,String code,StringRedisTemplate stringRedisTemplate){
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(phone) || stringRedisTemplate == null){
            return false;
        }

        String redisCode = stringRedisTemplate.opsForValue().get(getModifyPwdKey(phone));
        if (StringUtils.isEmpty(redisCode)){
            return false;
        }

        if (!redisCode.equals(code)){
            return false;
        }

        //设置验证状态
        setModifyPwdValidStatusReidsKey(phone, YesOrNoEnum.YES.getCode().toString(),stringRedisTemplate);

        return true;
    }

    /**
     * 验证修改密码是否被走过短信验证码的流程
     *
     * @param phone
     * @param stringRedisTemplate
     * @return
     */
    public static boolean validModifyPwdSmsCodeStatus(String phone,StringRedisTemplate stringRedisTemplate){
        if (StringUtils.isEmpty(phone) || stringRedisTemplate == null){
            return false;
        }
        String status = stringRedisTemplate.opsForValue().get(getModifyPwdValidStatusReidsKey(phone));
        if (StringUtils.isEmpty(status)){
            return false;
        }

        if (!status.equals(YesOrNoEnum.YES.getCode().toString())){
            return false;
        }

        return true;

    }




}
