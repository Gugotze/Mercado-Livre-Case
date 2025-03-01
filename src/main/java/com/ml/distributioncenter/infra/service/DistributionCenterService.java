package com.ml.distributioncenter.infra.service;

import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionCenterService {

    public List<DistributionCenterResponse> getDistributionCentersByItemId(Long itemId) {
        if (itemId == 1L) {
            return List.of(new DistributionCenterResponse(List.of("DC1", "DC2", "DC3")));
        } else if (itemId == 2L) {
            return List.of(new DistributionCenterResponse(List.of("DC4", "DC5")));
        } else {
            return List.of(new DistributionCenterResponse(List.of("DC1")));
        }
    }
}