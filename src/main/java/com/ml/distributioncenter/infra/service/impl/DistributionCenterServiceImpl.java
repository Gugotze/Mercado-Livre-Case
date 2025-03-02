package com.ml.distributioncenter.infra.service.impl;

import com.ml.distributioncenter.infra.exception.ItemNotFoundException;
import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.service.DistributionCenterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DistributionCenterServiceImpl implements DistributionCenterService {

    public List<DistributionCenterResponse> getDistributionCentersByItemId(Long itemId) {
        if (itemId == 1L) {
            return List.of(new DistributionCenterResponse(List.of("DC1", "DC2", "DC3")));
        } else if (itemId == 2L) {
            return List.of(new DistributionCenterResponse(List.of("DC4", "DC5")));
        } else {
            // Lançando a exceção personalizada caso o itemId não seja encontrado
            throw new ItemNotFoundException("O item com o ID " + itemId + " não está cadastrado em nosso sistema.");
        }
    }
}
