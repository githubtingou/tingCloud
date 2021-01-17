package com.ting.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试gateway
 *
 * @author ting
 * @version 1.0
 * @date 2021/01/11
 */
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class ApiController {

    @GetMapping(value = "/client")
    public String getApi() {
        return "gateWay->client";
    }

    @GetMapping(value = "/client/thread")
    public String getThread() throws InterruptedException {
        Thread.sleep(120000000);

        return "gateWay->client";
    }
}
