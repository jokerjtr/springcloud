package com.jtr.controller;

import com.jtr.entriy.CommonResult;
import com.jtr.entriy.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;
    @Value("${server.port}")
    private String serverPort;
    private static final String url="http://consul-provider-payment";
    @RequestMapping(value = "/consumer/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        System.out.println(url);
       return restTemplate.getForObject(url+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping(value = "/consumer/create")
    public CommonResult create(Payment payment){
        return  restTemplate.postForObject(url+"/payment/create",payment,CommonResult.class);
    }
}
