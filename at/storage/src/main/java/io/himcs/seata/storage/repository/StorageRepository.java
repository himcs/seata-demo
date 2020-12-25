package io.himcs.seata.storage.repository;

import io.himcs.seata.storage.entiry.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer>, JpaSpecificationExecutor<Storage> {
    Storage findByGoodsCode(String goodsGood);
}