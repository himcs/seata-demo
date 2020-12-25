package io.himcs.seata.bussiness.client;

import io.himcs.seata.common.dto.StorageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("${services.storage-service}")
public interface StorageService {
    @PostMapping("/decrease")
    void decrease(@RequestBody StorageDTO storageDTO);
}
