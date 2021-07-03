package com.ting.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

/**
 * springsecurity配置类
 *
 * @author ting
 * @version 1.0
 * @date 2021/02/19
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 重写用户验证和用户权限方法
     *
     * @return
     */
    @Bean
    public UserDetailsService customUserService() {
        // 注册UserDetailsService 的bean
        return new UserConfig();
    }

    /**
     * 登录相关配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 权限配置：不需要登录就可访问的地址
                .antMatchers("/", "/login")
                .permitAll()
                // 其他的请求需要登录授权
                .anyRequest()
                .authenticated()

                // 登录配置-指定支持基于表单的身份验证
                .and().formLogin()
                // 登录地址
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录处理页面
                //.loginProcessingUrl("/toLogin")
                // 登录失败跳转地址
                .failureUrl("/login?error=true")
                // 登录成功跳转地址
                .successHandler(new ForwardAuthenticationSuccessHandler("/index"))
                .permitAll()
                .and().headers().defaultsDisabled().cacheControl()// 解决用户安全退出后点击返回仍进入需验证的页面
                .and().frameOptions().sameOrigin()

                // 记住我功能
//                .and().rememberMe()
//                .key("sessionId")
//                .tokenValiditySeconds(60 * 60 * 24)

                // 退出账号
                .and().logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and().csrf().disable();

    }

    /**
     * 密码加密规则
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }


    /**
     * Spring security 配置
     *
     * @return
     * @author ting
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 显示UserNotFoundExceptions
//        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(customUserService());
        // 密码加密
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * 不需要登录就可访问
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/index");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
