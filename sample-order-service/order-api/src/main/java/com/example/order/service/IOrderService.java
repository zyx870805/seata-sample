package com.example.order.service;

import com.example.dto.OrderDto;
import com.example.response.ObjectResponse;

public interface IOrderService {

    ObjectResponse<OrderDto> createOrder(OrderDto orderDto);

}
