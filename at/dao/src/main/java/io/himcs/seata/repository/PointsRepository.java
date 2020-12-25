package io.himcs.seata.repository;

import io.himcs.seata.entity.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<Points, Integer>, JpaSpecificationExecutor<Points> {

}