package com.springcloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getorder")
    public String getOrder(){
        System.out.println("获取order");
        String msg = restTemplate.getForObject("http://127.0.0.1:8012/stock/reduct",String.class);
        return "order " + msg;
    }

}
