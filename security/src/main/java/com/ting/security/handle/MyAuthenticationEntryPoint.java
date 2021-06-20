package com.ting.security.handle;

import com.ting.security.common.ResultEnum;
import com.ting.security.utils.ResponseUtil;
import com.ting.security.utils.ResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限认证异常处理器
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.write(response, ResultUtils.packageResultByEnum(ResultEnum.CODE_ERROR_SHIRO));
    }
}
