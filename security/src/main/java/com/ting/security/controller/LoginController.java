package com.ting.security.controller;

import com.alibaba.fastjson.JSON;
import com.ting.security.common.ResultDto;
import com.ting.security.common.ResultEnum;
import com.ting.security.config.UserDetailServiceImpl;
import com.ting.security.dto.LoginFormDto;
import com.ting.security.dto.UserDto;
import com.ting.security.utils.JwtTokenUtils;
import com.ting.security.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 登录页面
     *
     * @param loginFormDto
     * @return
     */
    @PostMapping(value = "login")
    public ResultDto login(@RequestBody LoginFormDto loginFormDto) throws Exception {
        log.info("登录信息：{}", JSON.toJSON(loginFormDto));
        authenticate(loginFormDto.getUserName(), loginFormDto.getPassword());
        UserDto userDetails = ((UserDto) userDetailService.loadUserByUsername(loginFormDto.getUserName()));
        String token = jwtTokenUtils.createToken(userDetails);
        return ResultUtils.packageResultByEnum(ResultEnum.CODE_SUCCESS, token);

    }

    /**
     * 身份认证
     *
     * @param username
     * @param password
     * @throws Exception
     */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
