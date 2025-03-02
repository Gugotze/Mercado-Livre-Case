package com.ml.distributioncenter.infra.service.impl;

import com.ml.distributioncenter.infra.domain.CustomerOrder;
import com.ml.distributioncenter.infra.domain.OrderItem;
import com.ml.distributioncenter.infra.domain.request.OrderItemRequest;
import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.domain.response.OrderResponse;
import com.ml.distributioncenter.infra.repository.OrderRepository;
import com.ml.distributioncenter.infra.service.DistributionCenterService;
import com.ml.distributioncenter.infra.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DistributionCenterService distributionCenterService;

    public OrderResponse processOrder(List<OrderItemRequest> orderItems) {
        if (orderItems.size() > 100) {
            throw new IllegalArgumentException("O pedido não pode ter mais de 100 itens.");
        }

        CustomerOrder customerOrder = new CustomerOrder();
        List<OrderItem> items = getOrderItems(orderItems, customerOrder);
        orderRepository.save(customerOrder);

        return new OrderResponse(customerOrder.getId(), items);
    }

    public OrderResponse getOrder(Long orderId) {
        CustomerOrder customerOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new OrderResponse(customerOrder.getId(), customerOrder.getItems());
    }

    public List<DistributionCenterResponse> getDistributionCentersByItemId(Long itemId) {
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

