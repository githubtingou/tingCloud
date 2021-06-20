package com.ting.security.utils;

import com.ting.security.common.ResultDto;
import com.ting.security.common.ResultEnum;

public class ResultUtils {


    public static ResultDto packageResultByEnum(ResultEnum resultEnum) {
        return packageResultByEnum(resultEnum, null);

    }

    public static ResultDto packageResultByEnum(ResultEnum resultEnum, Object data) {
        return packageResult(resultEnum.getCode(), resultEnum.isSuccess(), resultEnum.getMsg(), data);
    }

    public static ResultDto packageResult(int code, boolean success, String msg, Object data) {
        return new ResultDto(code, success, msg, data);

    }

    public static ResultDto packageResult(int code, boolean success, String msg) {
        return packageResult(code, success, msg, null);

    }
}
