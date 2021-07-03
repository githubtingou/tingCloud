package com.ting.security.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ting.security.domain.entity.User;
import com.ting.security.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限验证
 *
 * @author ting
 * @version 1.0
 * @date 2021/02/19
 */
@Slf4j
public class UserConfig implements UserDetailsService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserService iUserService;


    /**
     * 用户权限验证
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 没有找到该用户异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名：[{}]", username);
        String password = request.getParameter("password");

        User user = iUserService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getName, username));

        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("没有找到对应的用户名称!");
        }

        if (!ObjectUtils.isEmpty(user.getStatus()) && "1".equals(user.getStatus())) {
            throw new AccountExpiredException("该用户已被锁定!");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码错误!");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username", username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // 这里返回登录名，密码和用户权限
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), authorities);
    }

}
