package com.github.zuihou.auth.api;

import com.github.zuihou.auth.dto.TokenDTO;
import com.github.zuihou.base.Result;

import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/09/07
 */
@Component
public class AuthTokenApiHystrix implements AuthTokenApi {
    @Override
    public Result<TokenDTO> login(String userName) {
        return Result.timeout();
    }
}
