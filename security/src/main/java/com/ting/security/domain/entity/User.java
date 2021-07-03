package com.ting.security.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ting
 * @since 2021-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Integer age;

    private String password;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 记住密码
     */
    private String token;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
