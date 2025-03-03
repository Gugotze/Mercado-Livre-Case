package com.ml.distributioncenter.infra.service;

import com.ml.distributioncenter.infra.domain.response.DistributionCenterResponse;

public interface DistributionCenterService {

    DistributionCenterResponse getDistributionCentersByItemId(Long itemId);

}
