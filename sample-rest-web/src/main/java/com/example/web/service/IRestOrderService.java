package com.example.web.service;

import com.example.dto.OrderRequest;
import com.example.response.ObjectResponse;

public interface IRestOrderService {
    ObjectResponse handleBusiness(OrderRequest orderRequest) throws Exception;
}
