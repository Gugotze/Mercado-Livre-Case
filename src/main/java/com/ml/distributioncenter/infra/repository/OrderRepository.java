package com.ml.distributioncenter.infra.repository;

import com.ml.distributioncenter.infra.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}
