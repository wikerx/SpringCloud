package com.github.zuihou.eureka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/09/06
 */
@EnableWebSecurity
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //springcloud 2.x 后默认启用了 csrf 校验
//        http.csrf().disable();
////        http.cors().disable();
//        super.configure(http);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.csrf().disable();
        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,如果是form方式,不能使用url格式登录
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder builder = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(builder.username("admin").password("admin").roles("USER").build());
//        return manager;
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
////                .passwordEncoder(new SystemPasswordEncoder())
//                //admin
//                .withUser("admin").password("admin").roles("EUREKA-CLIENT").and()
//                //eureka-security-client
//                .withUser("eureka-security-client").password("eureka-security-client").roles("EUREKA-CLIENT")
//        ;
//    }
}
