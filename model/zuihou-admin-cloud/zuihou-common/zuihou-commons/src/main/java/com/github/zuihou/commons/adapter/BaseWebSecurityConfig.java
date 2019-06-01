package com.github.zuihou.commons.adapter;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/01/12
 */
public abstract class BaseWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * //http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
     * //再次访问index页面无需登录直接访问
     * //访问http://localhost:8080/home 不拦截，直接访问，
     * //访问http://localhost:8080/hello 需要登录验证后，且具备 “ADMIN”权限hasAuthority("ADMIN")才可以访问
     * <p>
     * //        http
     * ////                .addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
     * //                .authorizeRequests()
     * //                .antMatchers("/actuator/**").permitAll()
     * //                .anyRequest().authenticated()
     * //                //.antMatchers("/hello").hasAuthority("ADMIN")
     * //                .and()
     * //                .formLogin()
     * //                .loginPage("/login")
     * //                .permitAll()
     * ////                .successHandler(loginSuccessHandler())//code3
     * //                .and()
     * //                .logout()
     * //                .logoutSuccessUrl("/home")
     * //                .permitAll()
     * //                .invalidateHttpSession(true)
     * //                .and()
     * //                .rememberMe()
     * //                .tokenValiditySeconds(1209600);
     * <p>
     * //允许访问项目主路径/swagger-ui.html的请求
     * //其它请求都要经过拦截验证
     * //同时也允许注销请求
     * //支持表单验证登录
     * //取消掉默认的csrf认证
     *
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
//                .anyRequest().authenticated()  //任意请求都要通过认证
                .antMatchers("/actuator/**")
                .hasAnyRole("USER")
//                .hasAuthority("USER")  //指定 请求需要验证
                .and()
                .logout().permitAll()
                .and()
                .formLogin();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
