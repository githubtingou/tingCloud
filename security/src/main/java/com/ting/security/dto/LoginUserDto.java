package com.ting.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LoginUserDto {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 是否记住密码
     */
    private Boolean remember;
}
