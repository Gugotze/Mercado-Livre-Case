package com.ml.distributioncenter.infra.controller;

import com.ml.distributioncenter.infra.domain.request.OrderItemRequest;
import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.domain.response.OrderResponse;
import com.ml.distributioncenter.infra.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse processOrder(@RequestBody List<OrderItemRequest> orderItems) {
        return orderService.processOrder(orderItems);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/distribution-centers")
    public List<DistributionCenterResponse> getDistributionCentersByItemId(@RequestParam Long itemId) {
        return orderService.getDistributionCentersByItemId(itemId);
    }
}