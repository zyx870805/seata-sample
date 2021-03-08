package com.example.order.service.impl;

import com.example.account.service.IAccountService;
import com.example.constants.ResCode;
import com.example.dto.AccountDto;
import com.example.dto.OrderDto;
import com.example.order.converter.OrderConvert;
import com.example.order.dal.entities.Order;
import com.example.order.dal.mappers.OrderMapper;
import com.example.order.service.IOrderService;
import com.example.response.ObjectResponse;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Reference
    private IAccountService accountService;

    @Autowired
    private OrderConvert orderConvert;

    @Override
    public ObjectResponse<OrderDto> createOrder(OrderDto orderDto) {
        log.info("全局事务ID: " + RootContext.getXID());
        ObjectResponse response = new ObjectResponse();
        try {
            //账户扣款
            AccountDto accountDto = new AccountDto();
            accountDto.setUserId(orderDto.getUserId());
            accountDto.setBalance(orderDto.getOrderAmount());
            ObjectResponse accountResponse = accountService.decreaseAccount(accountDto);
            //创建订单
            Order order = orderConvert.dto2Order(orderDto);
            order.setOrderNo(UUID.randomUUID().toString());
            orderMapper.createOrder(order);
            //判断扣款状态(判断前置)
            if (accountResponse.getCode() != ResCode.SUCCESS.getCode()) {
                response.setMsg(ResCode.FAILED.getMessage());
                response.setCode(ResCode.FAILED.getCode());
                return response;
            }
            response.setMsg(ResCode.SUCCESS.getMessage());
            response.setCode(ResCode.SUCCESS.getCode());
        } catch (Exception e) {
            log.error("createOrder Order: Occur Exception: ", orderDto, e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage() + "-" + ExceptionUtils.getFullStackTrace(e));
        }


        return null;
    }
}
