package com.ting.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserDto extends LoginSuccessDto implements UserDetails {

    private String userName;

    private String password;

    private Boolean remember;


    /**
     * 获取权限信息
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleList().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    /**
     * 获取用户名
     *
     * @return
     */
    @Override
    public String getUsername() {
        return this.userName;
    }


    /**
     * 账户是否到期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 用户是否被禁用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
