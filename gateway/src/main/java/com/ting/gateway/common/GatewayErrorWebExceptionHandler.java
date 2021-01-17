//package com.ting.gateway.common;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.WebProperties;
//import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.reactive.error.ErrorAttributes;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * gateway统一异常处理
// *
// * @author ting
// * @version 1.0
// * @date 2021/01/12
// */
//@Component
//@Slf4j
//public class GatewayErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {
//
//    public GatewayErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ErrorProperties errorProperties, ApplicationContext applicationContext) {
//        super(errorAttributes, resources, errorProperties, applicationContext);
//    }
//
//    /**
//     * 获取异常属性
//     *
//     * @param request
//     * @param options
//     * @return
//     */
//    @Override
//    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
//        Map<String, Object> attributes = request.attributes();
//        attributes.forEach((key, value) -> {
//            log.info("key:{}----value:{}", key, value);
//        });
//        return super.getErrorAttributes(request, options);
//    }
//
//    /**
//     * 从错误映射表获取HTTP错误状态信息。
//     *
//     * @param errorAttributes 当前错误信息
//     * @return 错误的HTTP状态
//     */
//    @Override
//    protected int getHttpStatus(Map<String, Object> errorAttributes) {
//        Object code = errorAttributes.get("code");
//        log.info("状态码：{}", code);
//        return super.getHttpStatus(errorAttributes);
//    }
//
//    /**
//     * 返参处理
//     *
//     * @param errorAttributes
//     * @return
//     */
//    @Override
//    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
//        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
//        //return super.getRoutingFunction(errorAttributes);
//    }
//
//
//    /**
//     * 构建返参
//     *
//     * @param code
//     * @param msg
//     * @return
//     */
//    public Map<String, Object> buildeResult(int code, String msg) {
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", code);
//        result.put("msg", msg);
//        result.put("data", null);
//        return result;
//    }
//}
