package com.ting.security.utils;

import com.alibaba.fastjson.JSON;
import com.ting.security.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
@ConfigurationProperties(prefix = JwtTokenUtils.PREFIX)
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class JwtTokenUtils {
    public static final String PREFIX = "jwt";

    /**
     * head名称
     */
    private String tokenHead;

    /**
     * token前缀
     */
    private String tokenPrefix;

    /**
     * 秘钥
     */
    private String secret;

    /**
     * token 过期时间
     */
    private Long expiration;

    /**
     * 记住我的过期时间
     */
    private Long rememberExpiration;

    /**
     * 生成token
     *
     * @param userDto
     * @return
     */
    public String createToken(UserDto userDto) {
        Long time = userDto.getRemember() ? this.rememberExpiration : this.expiration;
        Map<String, Object> map = new HashMap<>(1);
        map.put("user", userDto);
        return Jwts.builder()
                .setClaims(map)
                .setSubject(userDto.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + time * 100))
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();

    }


    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims generateToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();

    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        return generateToken(token).getSubject();
    }

    /**
     * 解析token为UserDto
     */

    public UserDto getUserDtoByToken(String token) {
        Claims claims = generateToken(token);
        claims.get("user");
        return JSON.parseObject(JSON.toJSONString(claims), UserDto.class);

    }


}
