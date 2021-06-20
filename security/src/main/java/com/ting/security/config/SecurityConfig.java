package com.ting.security.config;

import com.ting.security.filter.JwtAuthenticationFilter;
import com.ting.security.filter.JwtLoginFilter;
import com.ting.security.handle.MyAuthenticationEntryPoint;
import com.ting.security.handle.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("@{html.prefix}")
    private String webPrefix;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/web/**", "/static/**").permitAll()
                .anyRequest().authenticated()
                // 权限认证
                .anyRequest().access("@myAuthorityService.hasPermission(request,authentication)")
                .and()
                // jwt 过滤器
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .exceptionHandling()
                // 权限不足
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                // 关闭session认证
                .and().sessionManagement().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
