package com.ting.security.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ting.security.common.ResultEnum;
import com.ting.security.domain.SysUser;
import com.ting.security.dto.LoginFormDto;
import com.ting.security.dto.LoginSuccessDto;
import com.ting.security.dto.LoginUserDto;
import com.ting.security.dto.UserDto;
import com.ting.security.mapper.SysUserMapper;
import com.ting.security.utils.ApplicationContextUtils;
import com.ting.security.utils.JwtTokenUtils;
import com.ting.security.utils.ResponseUtil;
import com.ting.security.utils.ResultUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 登录请求处理
 */
@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private SysUserMapper sysUserMapper;

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 请求登录前
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            LoginFormDto loginFormDto = new ObjectMapper().readValue(request.getInputStream(), LoginFormDto.class);

            checkLoginForm(loginFormDto, response);

            LoginUserDto loginUserDto = new LoginUserDto();
            BeanUtils.copyProperties(loginFormDto, loginUserDto);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(JSON.toJSONString(loginUserDto), loginFormDto.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查必填箱，如果为空，返回提示
     *
     * @param loginFormDto
     * @param response
     */
    private void checkLoginForm(LoginFormDto loginFormDto, HttpServletResponse response) {
        if (ObjectUtils.isEmpty(loginFormDto.getUserName())) {
            ResponseUtil.write(response, "用户名不能为空");
            return;
        }
        if (ObjectUtils.isEmpty(loginFormDto.getPassword())) {
            ResponseUtil.write(response, "密码不能为空");
        }

    }

    /**
     * 登录成功处理
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDto userDto = (UserDto) authResult.getPrincipal();
        if (ObjectUtils.isEmpty(jwtTokenUtils)) {
            jwtTokenUtils = ApplicationContextUtils.getBean("JwtTokenUtils", JwtTokenUtils.class);
        }

        if (ObjectUtils.isEmpty(sysUserMapper)) {
            sysUserMapper = ApplicationContextUtils.getBean("sysUserMapper", SysUserMapper.class);
        }
        //更新登陆时间
        SysUser sysUser = new SysUser();
        sysUser.setId(userDto.getId())
                .setLastLoginTime(new Date());
        sysUserMapper.updateById(sysUser);

        // 封装token
        response.addHeader(jwtTokenUtils.getTokenHead(),
                jwtTokenUtils.getTokenPrefix() + jwtTokenUtils.createToken(userDto));

        LoginSuccessDto loginSuccessVO = new LoginSuccessDto();
        BeanUtils.copyProperties(userDto, loginSuccessVO);
        ResponseUtil.write(response, ResultUtils.packageResultByEnum(ResultEnum.CODE_SUCCESS, loginSuccessVO));
    }

    /**
     * 登录失败
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("登录失败，", failed);
        ResponseUtil.write(response, ResultUtils.packageResultByEnum(ResultEnum.CODE_ERROR));
    }
}
