package com.ml.distributioncenter.application.service.impl;

import com.ml.distributioncenter.domain.entity.CustomerOrder;
import com.ml.distributioncenter.adapter.request.OrderItemRequest;
import com.ml.distributioncenter.adapter.response.DistributionCenterResponse;
import com.ml.distributioncenter.adapter.response.OrderProcessResponse;
import com.ml.distributioncenter.adapter.response.OrderResponse;
import com.ml.distributioncenter.domain.repository.OrderRepository;
import com.ml.distributioncenter.infra.client.DistributionCenterClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private DistributionCenterClient distributionCenterClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessOrder() {
        List<OrderItemRequest> orderItems = List.of(new OrderItemRequest(1L, "Item1", 10));
        when(distributionCenterClient.getDistributionCentersByItemId(anyLong())).thenReturn(new DistributionCenterResponse());

        OrderProcessResponse response = orderService.processOrder(orderItems);

        assertNotNull(response);
        assertEquals(1, response.getItems().size());
        verify(orderRepository, times(1)).save(any(CustomerOrder.class));
    }

    @Test
    void testGetOrder() {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(1L);
        customerOrder.setItems(new ArrayList<>());
        when(orderRepository.findById(1L)).thenReturn(Optional.of(customerOrder));

        OrderResponse response = orderService.getOrder(1L);

        assertNotNull(response);
        assertEquals(1L, response.getOrderId());
    }

    @Test
    void testProcessOrderWithMoreThan100Items() {
        List<OrderItemRequest> orderItems = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            orderItems.add(new OrderItemRequest((long) i, "Item" + i, 1));
        }

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderItems);
        });

        assertEquals("O pedido não pode ter mais de 100 itens.", exception.getMessage());
    }

}