package com.ml.distributioncenter.infra.client;

import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;
import com.ml.distributioncenter.infra.service.DistributionCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/distribution-centers")
public class DistributionCenterClient {

    private final DistributionCenterService distributionCenterService;

    @GetMapping
    public DistributionCenterResponse getDistributionCentersByItemId(@RequestParam Long itemId) {
        return distributionCenterService.getDistributionCentersByItemId(itemId);
    }
}