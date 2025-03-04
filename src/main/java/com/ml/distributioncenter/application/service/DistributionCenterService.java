package com.ml.distributioncenter.application.service;

import com.ml.distributioncenter.adapter.response.DistributionCenterResponse;

public interface DistributionCenterService {

    DistributionCenterResponse getDistributionCentersByItemId(Long itemId);

}
