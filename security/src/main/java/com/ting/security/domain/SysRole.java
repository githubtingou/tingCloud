package com.ting.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@ApiModel(value = "com-ting-security-domain-SysRole")
@Data
public class SysRole extends Model<SysRole> implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    /**
     * 角色状态;0正常；1禁用
     */
    @ApiModelProperty(value = "角色状态;0正常；1禁用")
    private Integer roleState;

    /**
     * 新增时间
     */
    @ApiModelProperty(value = "新增时间")
    private Date addTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    @TableLogic
    private Integer delFlag;

    private static final long serialVersionUID = 1L;
}
