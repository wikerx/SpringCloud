package com.github.zuihou.auth.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/12.
 */
@Configuration
public class ClientProperties {
    @Getter
    @Value("${client.id}")
    private String clientId;
    @Getter
    @Value("${client.secret}")
    private String clientSecret;
    @Getter
    @Value("${client.token-header}")
    private String clientTokenHeader;

}
