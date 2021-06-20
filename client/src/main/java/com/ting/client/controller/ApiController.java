package com.ting.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);

        }};

        List<Integer> list1 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);

        }};
        list.stream().filter(t -> {
                    Iterator<Integer> iterator = list1.iterator();
                    Integer next = iterator.next();
                    System.out.println("--------" + next);
                    return t.equals(next);
                }


        ).collect(Collectors.toList()).forEach(System.out::println);
    }
}
