package com.ting.security.dto;

import lombok.Data;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Data
public class PermissionMap {

    public static HashMap<String, Collection<ConfigAttribute>> map;

    public static List<PermissionDto> list;
}