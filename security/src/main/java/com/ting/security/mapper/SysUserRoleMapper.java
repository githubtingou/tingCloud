package com.ting.security.mapper;

import com.ting.security.dto.UserDto;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {

    UserDto getUserAndRoleByUserName(@Param(value = "username") String username);
}
