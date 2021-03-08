package com.example.order.converter;

import com.example.dto.OrderDto;
import com.example.order.dal.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderConvert {

    @Mappings({
            @Mapping(source = "orderCount", target="count"),
            @Mapping(source = "orderAmount", target = "amount")
    })
    Order dto2Order(OrderDto orderDto);
}
