package com.ting.security.commom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 统一异常管理
 *
 * @author TingOu
 * @version 1.0
 * @date 2019-05-12
 */
@Controller
@Slf4j
public class GlobalExceptionHandler implements ErrorController {
    private static final String POST = "POST";
    private static final String GET = "GET";


    @Override
    public String getErrorPath() {
        // 出现异常时跳到 @RequestMapping("/error") 进行处理
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Exception e) {
        log.error("GlobalExceptionHandler 统一处理系统错误，错误代码", e);
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String method = request.getMethod();
        if (POST.equals(method)) {
            ServletInputStream inputStream = null;
            try {
                inputStream = request.getInputStream();
            } catch (IOException ex) {
                log.error("post类型获取参数错误", ex);
            }
            request.setAttribute("data", postParm(inputStream));
        } else {
            request.setAttribute("data", request.getQueryString());
        }
        request.setAttribute("URL", request.getRequestURL());
        request.setAttribute("error", e);
        request.setAttribute("method", method);
        request.setAttribute("contentType", request.getContentType());
        request.setAttribute("status", statusCode);
        return "errorPage/error";
    }

    /**
     * 获取post类型的参数
     *
     * @param inputStream
     * @return
     */
    public String postParm(ServletInputStream inputStream) {
        StringBuilder content = new StringBuilder();
        try {
            byte[] b = new byte[1024];
            int lens = -1;
            while ((lens = inputStream.read(b)) > 0) {
                content.append(new String(b, 0, lens));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error("postParm()方法中inputStream.read()错误：", ex);
        }
        return content.toString();

    }

//    @ExceptionHandler(value = Exception.class)
//    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) throws Exception {
//        Map<String, Object> errorMap = new HashMap<String, Object>();
//        errorMap.put("exception", e);
//        errorMap.put("url", req.getRequestURL());
//        System.out.println("错误提示：：---" + e);
//        model.addAttribute("exception", e);
//        return "errorPage/404";
//    }
//    @RequestMapping("/error")
//    public String error(){
//        return "/error/404.jsp";
//    }

}
