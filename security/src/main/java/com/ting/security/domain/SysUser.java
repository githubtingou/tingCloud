package com.ting.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ApiModel(value = "com-ting-secuirty-domain-SysUser")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SysUser extends Model<SysUser> implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId
    private Long id;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 性别；0：女；1男
     */
    @ApiModelProperty(value = "性别；0：女；1男")
    private Integer sex;

    /**
     * 住址
     */
    @ApiModelProperty(value = "住址")
    private String address;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    /**
     * 登录失败次数
     */
    @ApiModelProperty(value = "登录失败次数")
    private Integer failedLoginNum;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private Date addTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 删除标记，0：未删除；1删除
     */
    @ApiModelProperty(value = "删除标记，0：未删除；1删除")
    @TableLogic
    private int delFlag;

    private static final long serialVersionUID = 1L;
}

