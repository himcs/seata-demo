package io.himcs.seata.bussiness.client;

import io.himcs.seata.common.dto.PointsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("${services.points-service}")
public interface PointsService {
    @PostMapping("increase")
    void increase(@RequestBody PointsDTO pointsDTO);
}
