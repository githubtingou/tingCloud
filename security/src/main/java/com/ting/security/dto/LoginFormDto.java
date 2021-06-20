package com.ting.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户登录类
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LoginFormDto extends LoginUserDto {


    /**
     * 密码
     */
    private String password;


}
