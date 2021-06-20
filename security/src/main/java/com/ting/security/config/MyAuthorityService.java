package com.ting.security.config;

import com.ting.security.dto.PermissionDto;
import com.ting.security.dto.PermissionMap;
import com.ting.security.mapper.PermissionMapper;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component("myAuthorityService")
public class MyAuthorityService {

    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * 判断用户是否有权限
     *
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Collection<ConfigAttribute> collection = getAttributes(request);

        if (ObjectUtils.isEmpty(collection)) {
            return true;
        }

        for (ConfigAttribute next : collection) {
            String attribute = next.getAttribute();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (attribute.trim().equals(grantedAuthority.getAuthority())) {
                    return true;
                }
            }
        }

        throw new AccessDeniedException("权限不足");

    }

    /**
     * 获取权限
     *
     * @param request
     * @return
     */
    public Collection<ConfigAttribute> getAttributes(HttpServletRequest request) {
        HashMap<String, Collection<ConfigAttribute>> map = PermissionMap.map;
        if (map == null) {
            map = loadResourceDefine(map);
        }
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }

        return null;
    }

    /**
     * 加载权限表中所有权限
     */
    private HashMap<String, Collection<ConfigAttribute>> loadResourceDefine(HashMap<String, Collection<ConfigAttribute>> map) {
        map = new HashMap<>();
        List<PermissionDto> all = permissionMapper.seleteAllRoleByUrl();
        for (PermissionDto permissionDto : all) {
            List<ConfigAttribute> configAttributeList = permissionDto.getRoleNames()
                    .stream()
                    .map(roleName -> (ConfigAttribute) new SecurityConfig(roleName.toUpperCase()))
                    .collect(Collectors.toList());

            map.put(permissionDto.getPermissionUrl(), configAttributeList);
        }
        PermissionMap.map = map;
        return map;
    }
}
