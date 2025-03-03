package com.ml.distributioncenter.infra.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private String name;
    private int quantity;
    private List<String> distributionCenters;

}

