package com.github.zuihou.manager;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.service.SmsTaskService;
import com.github.zuihou.sms.util.PhoneUtils;
import com.github.zuihou.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.zuihou.commons.exception.core.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.assertFalse;
import static com.github.zuihou.utils.BizAssert.assertNotEmpty;
import static com.github.zuihou.utils.BizAssert.assertNotNull;
import static com.github.zuihou.utils.BizAssert.assertTrue;


/**
 * 抽取controller 和 open 中的公共代码
 * 短信管理类，用于抽取合并open和controller层中方法的公共代码
 *
 * @author tangyh
 * @date 2018/12/24
 */
@Component
public class SmsManager {
    @Autowired
    private SmsTaskService smsTaskService;

    /**
     * 保存短信任务
     *
     * @param smsTask
     */
    public void saveTask(SmsTask smsTask) {
        //1，验证必要参数
        assertNotEmpty(BASE_VALID_PARAM.build("主题不能为空"), smsTask.getTopic());
        assertNotEmpty(BASE_VALID_PARAM.build("接收人不能为空"), smsTask.getReceiver());
        assertNotEmpty(BASE_VALID_PARAM.build("短信参数不能为空"), smsTask.getTemplateParams());
        assertNotNull(BASE_VALID_PARAM.build("供应商id不能为空"), smsTask.getProviderId());
        assertNotNull(BASE_VALID_PARAM.build("模板id不能为空"), smsTask.getTemplateId());
        Set<String> phoneList = PhoneUtils.getPhone(smsTask.getReceiver());
        assertFalse(BASE_VALID_PARAM, phoneList == null || phoneList.isEmpty());

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (smsTask.getSendTime() != null) {
            Date endDate = DateUtils.localDateTime2Date(LocalDateTime.now().plusMinutes(5));
            boolean flag = DateUtils.isDateTimeInRange(smsTask.getSendTime(), new Date(), endDate);
            assertTrue(BASE_VALID_PARAM.build("定时发送时间至少在当前时间的5分钟之后"), flag);
        }

        String templateParams = smsTask.getTemplateParams();
        JSONObject obj = JSONObject.parseObject(templateParams, Feature.OrderedField);
        assertNotNull(BASE_VALID_PARAM.build("短信参数格式必须为严格的json字符串"), obj);

        smsTask.setCreateUserName(BaseContextHandler.getName());
        smsTaskService.saveTask(smsTask);
    }
}
