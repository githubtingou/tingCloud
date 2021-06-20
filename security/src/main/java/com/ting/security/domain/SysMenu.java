package com.ting.security.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@ApiModel(value = "com-ting-security-domain-SysMenu")
@Data
public class SysMenu extends Model<SysMenu> implements Serializable {
    /**
     *
     */
    @ApiModelProperty(value = "主键")
    @TableId
    private Long id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private String parentId;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String menuUrl;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    /**
     * 菜单状态；0正常，1：隐藏
     */
    @ApiModelProperty(value = "菜单状态；0正常，1：隐藏")
    private Integer menuState;

    /**
     * 新增时间
     */
    @ApiModelProperty(value = "新增时间")
    private Date addTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTiem;

    /**
     * 删除标记；0正常，1：删除
     */
    @ApiModelProperty(value = "删除标记；0正常，1：删除")
    @TableLogic
    private Integer delFlag;

    private static final long serialVersionUID = 1L;
}

