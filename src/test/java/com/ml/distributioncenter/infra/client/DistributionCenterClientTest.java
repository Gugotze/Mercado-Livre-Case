package com.ml.distributioncenter.infra.client;

import com.ml.distributioncenter.adapter.response.DistributionCenterResponse;
import com.ml.distributioncenter.application.service.DistributionCenterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DistributionCenterClientTest {

    @Mock
    private DistributionCenterService distributionCenterService;

    @InjectMocks
    private DistributionCenterClient distributionCenterClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDistributionCentersByItemId() {
        when(distributionCenterService.getDistributionCentersByItemId(anyLong())).thenReturn(new DistributionCenterResponse());

        DistributionCenterResponse response = distributionCenterClient.getDistributionCentersByItemId(1L);

        assertNotNull(response);
        verify(distributionCenterService, times(1)).getDistributionCentersByItemId(1L);
    }
}