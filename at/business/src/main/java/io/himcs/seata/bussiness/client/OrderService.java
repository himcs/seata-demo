package io.himcs.seata.bussiness.client;

import io.himcs.seata.common.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("${services.order-service}")
public interface OrderService {
    @PostMapping("/create")
    OrderDTO create(@RequestBody OrderDTO order);
}
