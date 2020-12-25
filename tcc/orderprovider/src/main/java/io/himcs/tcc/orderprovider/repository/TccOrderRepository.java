package io.himcs.tcc.orderprovider.repository;

import io.himcs.tcc.orderprovider.entity.TccOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TccOrderRepository extends JpaRepository<TccOrder, Long>, JpaSpecificationExecutor<TccOrder> {
    TccOrder findByOrderCode(String orderCode);
}