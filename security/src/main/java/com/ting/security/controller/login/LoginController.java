package com.ting.security.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录
 *
 * @author ting
 * @version 1.0
 * @date 2021/02/19
 */
@Controller
public class LoginController {

    /**
     * 登录页面
     *
     * @return 登录页面
     */
    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

//    /**
//     * 登录页面
//     *
//     * @return 登录页面
//     */
//    @RequestMapping(value = {"/toLogin"})
//    @ResponseBody
//    public String toLogin() {
//        return "登录处理ing";
//    }

    @RequestMapping(value = "index")
    public String loginSuccess() {
        return "index";

    }

}
