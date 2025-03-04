package com.ml.distributioncenter.adapter.controller;

import com.ml.distributioncenter.adapter.request.OrderItemRequest;
import com.ml.distributioncenter.adapter.response.OrderProcessResponse;
import com.ml.distributioncenter.adapter.response.OrderResponse;
import com.ml.distributioncenter.application.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processOrder_ShouldReturnOrderProcessResponse() {
        List<OrderItemRequest> orderItems = Collections.singletonList(new OrderItemRequest());
        OrderProcessResponse expectedResponse = new OrderProcessResponse();

        when(orderService.processOrder(orderItems)).thenReturn(expectedResponse);

        OrderProcessResponse actualResponse = orderController.processOrder(orderItems);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(orderService, times(1)).processOrder(orderItems);
    }

    @Test
    void getOrder_ShouldReturnOrderResponse() {
        Long orderId = 1L;
        OrderResponse expectedResponse = new OrderResponse();

        when(orderService.getOrder(orderId)).thenReturn(expectedResponse);

        OrderResponse actualResponse = orderController.getOrder(orderId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(orderService, times(1)).getOrder(orderId);
    }
}
