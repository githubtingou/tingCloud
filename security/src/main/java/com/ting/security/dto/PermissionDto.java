package com.ting.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class PermissionDto {

    /**
     * url
     */
    private String permissionUrl;

    /**
     * 角色名称
     */
    private List<String> roleNames;
}