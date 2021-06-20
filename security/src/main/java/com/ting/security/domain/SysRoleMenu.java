package com.ting.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;

@ApiModel(value = "com-ting-security-domain-SysRoleMenu")
@Data
public class SysRoleMenu extends Model<SysRoleMenu> implements Serializable {
    /**
     *
     */
    @ApiModelProperty(value = "主键")
    @TableId
    private Long id;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    private static final long serialVersionUID = 1L;
}
