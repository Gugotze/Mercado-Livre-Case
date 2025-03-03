package com.ml.distributioncenter.infra.controller;

import com.ml.distributioncenter.infra.domain.request.OrderItemRequest;
import com.ml.distributioncenter.infra.domain.response.OrderProcessResponse;
import com.ml.distributioncenter.infra.domain.response.OrderResponse;
import com.ml.distributioncenter.infra.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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