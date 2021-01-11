package com.ting.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试gateway
 *
 * @author lishuang
 * @version 1.0
 * @date 2021/01/11
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping(value = "/client")
    public String getApi() {
        return "gateWay->client";
    }
}
