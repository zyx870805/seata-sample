package com.example.web.controller;

import com.example.dto.OrderRequest;
import com.example.response.ObjectResponse;
import com.example.web.service.IRestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    IRestOrderService restOrderService;

    @PostMapping("/order")
    ObjectResponse order(@RequestBody OrderRequest orderRequest) throws Exception {
        return restOrderService.handleBusiness(orderRequest);
    }
}
