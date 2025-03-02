package com.ml.distributioncenter.infra.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    private String name;

    private int quantity;

    @ElementCollection
    private List<String> distributionCenters;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

}
