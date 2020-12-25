package io.himcs.tcc.storageprovider.repository;

import io.himcs.tcc.storageprovider.entity.TccStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TccStorageRepository extends JpaRepository<TccStorage, Long>, JpaSpecificationExecutor<TccStorage> {
    TccStorage findByGoodsCode(String GoodsCode);
}