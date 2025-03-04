package com.ml.distributioncenter.application.service.impl;

import com.ml.distributioncenter.infra.client.DistributionCenterClient;
import com.ml.distributioncenter.domain.entity.CustomerOrder;
import com.ml.distributioncenter.domain.entity.OrderItem;
import com.ml.distributioncenter.adapter.request.OrderItemRequest;
import com.ml.distributioncenter.adapter.response.OrderProcessResponse;
import com.ml.distributioncenter.adapter.response.OrderResponse;
import com.ml.distributioncenter.domain.exception.OrderNotFoundException;
import com.ml.distributioncenter.domain.repository.OrderRepository;
import com.ml.distributioncenter.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DistributionCenterClient distributionCenterClient;

    public OrderProcessResponse processOrder(List<OrderItemRequest> orderItems) {
        if (orderItems.size() > 100) {
            throw new IllegalArgumentException("O pedido não pode ter mais de 100 itens.");
        }

        CustomerOrder customerOrder = new CustomerOrder();
        List<OrderItem> items = getOrderItems(orderItems, customerOrder);
        orderRepository.save(customerOrder);

        return new OrderProcessResponse(customerOrder.getId(), items);
    }

    public OrderResponse getOrder(Long orderId) {
        CustomerOrder customerOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));
        return new OrderResponse(customerOrder.getId(), customerOrder.getItems());
    }

    private List<OrderItem> getOrderItems(List<OrderItemRequest> orderItems, CustomerOrder customerOrder) {
        List<OrderItem> items = orderItems.stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setItemId(item.getItemId());
                    orderItem.setName(item.getName());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setDistributionCenters(distributionCenterClient.getDistributionCentersByItemId(item.getItemId()).getDistribuitionCenters());
                    orderItem.setCustomerOrder(customerOrder);
                    return orderItem;
                })
                .toList();
        customerOrder.setItems(items);
        return items;
    }
}
