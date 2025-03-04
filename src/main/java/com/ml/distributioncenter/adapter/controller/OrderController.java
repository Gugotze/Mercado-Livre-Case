package com.ml.distributioncenter.adapter.controller;

import com.ml.distributioncenter.adapter.request.OrderItemRequest;
import com.ml.distributioncenter.adapter.response.OrderProcessResponse;
import com.ml.distributioncenter.adapter.response.OrderResponse;
import com.ml.distributioncenter.application.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderProcessResponse processOrder(@RequestBody @Valid List<OrderItemRequest> orderItems) {
        return orderService.processOrder(orderItems);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

}