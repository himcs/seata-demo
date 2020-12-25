package io.himcs.seata.storage.controller;

import io.himcs.seata.common.dto.StorageDTO;
import io.himcs.seata.storage.entiry.Storage;
import io.himcs.seata.storage.repository.StorageRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class StorageController {

    @Resource
    private StorageRepository storageRepository;

    @PostMapping("/decrease")
    Storage decrease(@RequestBody StorageDTO storageDTO) {
        Storage byGoodsCode = storageRepository.findByGoodsCode(storageDTO.getGoodsCode());
        Integer quantity = byGoodsCode.getQuantity();
        Integer complete = quantity - storageDTO.getQuantity();
        if (complete < 0) {
            throw new RuntimeException("库存不足");
        }
        byGoodsCode.setQuantity(complete);
        Storage save = storageRepository.save(byGoodsCode);
        return save;
    }
}
