package com.github.zuihou.msgscenter.api.hystrix;

import java.util.List;

import com.github.zuihou.base.Result;
import com.github.zuihou.msgs.dto.MsgsMobileDTO;
import com.github.zuihou.msgscenter.api.MsgsApi;
import com.github.zuihou.msgscenter.dto.MsgsCenterDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoPageResultDTO;

import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/26
 */
@Component
public class MsgsApiHystrix implements MsgsApi {
    @Override
    public Result<MsgsCenterDTO> getLastMsgsAndCount(Long userId) {
        return Result.timeout();
    }

    @Override
    public Result<List<MsgsCenterInfoPageResultDTO>> getMsgsByBizId(String bizId, String bizType) {
        return Result.timeout();
    }

    @Override
    public Result<MsgsMobileDTO> getLastMobileAndCount(Long userId) {
        return Result.timeout();
    }
}
