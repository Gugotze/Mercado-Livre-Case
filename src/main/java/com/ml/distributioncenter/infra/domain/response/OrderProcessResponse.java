package com.ml.distributioncenter.infra.domain.response;

import com.ml.distributioncenter.infra.domain.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderProcessResponse {

    private Long orderId;
    private List<ProcessItemResponse> items;

    public OrderProcessResponse(Long orderId, List<OrderItem> items) {
        this.orderId = orderId;
        this.items = items.stream()
                .map(item -> new ProcessItemResponse(item.getName(), item.getDistributionCenters()))
                .collect(Collectors.toList());
    }
}
