package com.ml.distributioncenter.application.service;

import com.ml.distributioncenter.adapter.request.OrderItemRequest;
import com.ml.distributioncenter.adapter.response.OrderProcessResponse;
import com.ml.distributioncenter.adapter.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderProcessResponse processOrder(List<OrderItemRequest> orderItems);
    OrderResponse getOrder(Long orderId);
}