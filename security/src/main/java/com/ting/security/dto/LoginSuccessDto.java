package com.ting.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LoginSuccessDto {
    private Long id;

    private String phone;

    /**
     * 角色信息
     */
    private List<String> roleList;

}
