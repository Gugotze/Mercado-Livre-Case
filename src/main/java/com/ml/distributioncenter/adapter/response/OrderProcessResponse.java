package com.ml.distributioncenter.adapter.response;

import com.ml.distributioncenter.domain.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderProcessResponse {

    private Long orderId;
    private List<ProcessItemResponse> items;

    public OrderProcessResponse(Long orderId, List<OrderItem> items) {
        this.orderId = orderId;
        this.items = items.stream()
                .map(item -> new ProcessItemResponse(item.getName(), item.getDistributionCenters()))
                .toList();
    }
}
