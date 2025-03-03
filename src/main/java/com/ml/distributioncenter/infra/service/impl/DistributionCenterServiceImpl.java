package com.ml.distributioncenter.infra.service.impl;

import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.exception.ItemNotFoundException;
import com.ml.distributioncenter.infra.service.DistributionCenterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionCenterServiceImpl implements DistributionCenterService {

    @Override
    public DistributionCenterResponse getDistributionCentersByItemId(Long itemId) {
        if (itemId == 1L) {
            return new DistributionCenterResponse(List.of("CD1", "CD2", "CD3"));
        } else if (itemId == 2L) {
            return new DistributionCenterResponse(List.of("CD2", "CD3"));
        } else {
            throw new ItemNotFoundException("O item com o ID " + itemId + " não está cadastrado em nosso sistema.");
        }
    }
}