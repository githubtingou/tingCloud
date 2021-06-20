package com.ting.security.common;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

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

    /**
     * 数据
     */
    private Object data;


}
