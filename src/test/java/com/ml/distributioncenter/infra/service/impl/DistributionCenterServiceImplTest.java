package com.ml.distributioncenter.infra.service.impl;

import com.ml.distributioncenter.infra.client.DistributionCenterClient;
import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.service.DistributionCenterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DistributionCenterServiceImplTest {

    @Mock
    private DistributionCenterClient distributionCenterClient;

    @InjectMocks
    private DistributionCenterServiceImpl distributionCenterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDistributionCentersByItemId() {

        long itemId = 1L;

        DistributionCenterResponse expectedResponse = new DistributionCenterResponse();

        if (itemId == 1L) {
            expectedResponse.setDistribuitionCenters(List.of("CD1", "CD2", "CD3"));
        } else if (itemId == 2L) {
            expectedResponse.setDistribuitionCenters(List.of("CD2", "CD3"));
        }

        when(distributionCenterClient.getDistributionCentersByItemId(itemId)).thenReturn(expectedResponse);

        DistributionCenterResponse actualResponse = distributionCenterService.getDistributionCentersByItemId(itemId);

        assertEquals(expectedResponse, actualResponse);
    }
}