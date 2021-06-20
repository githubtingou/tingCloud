package com.ting.security.mapper;

import com.ting.security.dto.PermissionDto;
import lombok.Data;

import java.util.List;

public interface PermissionMapper {


    /**
     *
     * @return
     */
    List<PermissionDto> seleteAllRoleByUrl();
}