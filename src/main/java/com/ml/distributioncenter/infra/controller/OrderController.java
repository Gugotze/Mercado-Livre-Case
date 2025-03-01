package com.ml.distributioncenter.infra.controller;

import com.ml.distributioncenter.infra.domain.CustomerOrder;
import com.ml.distributioncenter.infra.domain.OrderItem;
import com.ml.distributioncenter.infra.domain.request.OrderItemRequest;
import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.domain.response.OrderResponse;
import com.ml.distributioncenter.infra.repository.OrderRepository;
import com.ml.distributioncenter.infra.service.DistributionCenterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final DistributionCenterService distributionCenterService;

    @PostMapping
    public OrderResponse processOrder(@RequestBody List<OrderItemRequest> orderItems) {
        CustomerOrder customerOrder = new CustomerOrder();
        List<OrderItem> items = getOrderItems(orderItems, customerOrder);
        orderRepository.save(customerOrder);

        return new OrderResponse(customerOrder.getId(), items);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        CustomerOrder customerOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new OrderResponse(customerOrder.getId(), customerOrder.getItems());
    }

    @GetMapping("/distribution-centers/{itemId}")
    public List<DistributionCenterResponse> getDistributionCentersByItemId(@PathVariable Long itemId) {
        return distributionCenterService.getDistributionCentersByItemId(itemId);
    }

    private List<OrderItem> getOrderItems(List<OrderItemRequest> orderItems, CustomerOrder customerOrder) {
        List<OrderItem> items = orderItems.stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setItemId(item.getItemId());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setDistributionCenters(distributionCenterService.getDistributionCentersByItemId(item.getItemId()).get(0).getDistribuitionCenters());
                    orderItem.setCustomerOrder(customerOrder);
                    return orderItem;
                })
                .collect(Collectors.toList());
        customerOrder.setItems(items);
        return items;
    }
}
