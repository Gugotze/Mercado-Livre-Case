package com.ml.distributioncenter.infra.service;

import com.ml.distributioncenter.infra.domain.request.OrderItemRequest;
import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.domain.response.OrderProcessResponse;
import com.ml.distributioncenter.infra.domain.response.OrderResponse;
import com.ml.distributioncenter.infra.domain.response.ProcessItemResponse;

import java.util.List;

public interface OrderService {
    OrderProcessResponse processOrder(List<OrderItemRequest> orderItems);
    OrderResponse getOrder(Long orderId);
    DistributionCenterResponse getDistributionCentersByItemId(Long itemId);
}