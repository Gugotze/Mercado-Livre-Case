package com.ml.distributioncenter.adapter.response;

import com.ml.distributioncenter.domain.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponse {

    private Long orderId;
    private List<OrderItemResponse> items;

    public OrderResponse(Long orderId, List<OrderItem> items) {
        this.orderId = orderId;
        this.items = items.stream()
                .map(item -> new OrderItemResponse(item.getName(), item.getQuantity(), item.getDistributionCenters()))
                .toList();
    }
}
