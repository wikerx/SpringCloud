package com.github.zuihou.admin.config;

import com.github.zuihou.commons.adapter.BaseWebSecurityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/09/07
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends BaseWebSecurityConfig {

}
