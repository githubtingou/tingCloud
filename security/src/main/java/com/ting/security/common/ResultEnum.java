package com.ting.security.common;

import lombok.Getter;

@Getter
public enum ResultEnum {
    CODE_SUCCESS(0, true, "处理成功"),
    CODE_ERROR_SHIRO(6000, true, "数据权限异常"),
    CODE_ACCESS_SHIRO(6001, true, "权限不足"),
    CODE_ERROR(4000, "处理失败");
    /**
     * 标识
     */
    private int code;

    /**
     * 成功表示
     */
    private boolean success;

    /**
     * 提示
     */
    private String msg;

    ResultEnum(int code, boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
