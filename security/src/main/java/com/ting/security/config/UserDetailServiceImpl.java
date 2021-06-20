package com.ting.security.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ting.security.domain.SysUser;
import com.ting.security.dto.LoginUserDto;
import com.ting.security.dto.UserDto;
import com.ting.security.mapper.SysUserMapper;
import com.ting.security.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 用户封装
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUserDto loginUser = new LoginUserDto()
                .setUserName(username);

        UserDto userDto = sysUserRoleMapper.getUserAndRoleByUserName(loginUser.getUserName());
        if (ObjectUtils.isEmpty(userDto)){
            throw new UsernameNotFoundException("没有该用户");
        }
        userDto.setRemember(loginUser.getRemember());

        return userDto;
    }
}
