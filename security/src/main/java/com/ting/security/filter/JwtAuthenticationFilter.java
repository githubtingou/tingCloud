package com.ting.security.filter;

import com.ting.security.dto.UserDto;
import com.ting.security.utils.ApplicationContextUtils;
import com.ting.security.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 效验过滤器
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("授权请求方式：{}，请求地址{}", request.getMethod(), request.getServletPath());
        if (jwtTokenUtils == null) {
            jwtTokenUtils = ApplicationContextUtils.getBean("jwtTokenUtils", JwtTokenUtils.class);
        }

        String header = request.getHeader(jwtTokenUtils.getTokenHead());

        // 没有token 直接放行
        if (ObjectUtils.isEmpty(header) || !header.startsWith(jwtTokenUtils.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        // 进行token权限效验
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    /**
     * 解析token
     *
     * @param header
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String token = header.replace(jwtTokenUtils.getTokenPrefix(), "");
        String userName = jwtTokenUtils.getUserName(token);

        if (!ObjectUtils.isEmpty(userName)) {

            UserDto userDto = jwtTokenUtils.getUserDtoByToken(token);
            return new UsernamePasswordAuthenticationToken(userName,
                    null, userDto.getAuthorities());
        }
        return null;
    }
}
