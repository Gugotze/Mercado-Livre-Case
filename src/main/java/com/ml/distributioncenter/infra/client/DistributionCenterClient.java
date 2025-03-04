package com.ml.distributioncenter.infra.client;

import com.ml.distributioncenter.adapter.response.DistributionCenterResponse;
import com.ml.distributioncenter.application.service.DistributionCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/distribution-centers")
// Deixei com RestController para poder testar via Postman. Para uma aplicação real o correto seria utilizar o RestTemplate e o @Component
public class DistributionCenterClient {

    private final DistributionCenterService distributionCenterService;

    @GetMapping
    public DistributionCenterResponse getDistributionCentersByItemId(@RequestParam Long itemId) {
        return distributionCenterService.getDistributionCentersByItemId(itemId);
    }

}