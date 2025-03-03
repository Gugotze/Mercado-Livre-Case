package com.ml.distributioncenter.infra.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    private Long itemId;

    @NotBlank()
    private String name;

    @Min(value = 1)
    private int quantity;
}
