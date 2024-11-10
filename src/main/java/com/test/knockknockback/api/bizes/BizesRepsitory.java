package com.test.knockknockback.api.bizes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BizesRepsitory extends JpaRepository<BizesEntity, String> {
    @Query("select b from BizesEntity b where b.placeNumber = :placeNumber")
    Optional<BizesEntity> findByPlaceNumber(String placeNumber);

    @Query("select b from BizesEntity b where b.bizesNumber = :bizesNumber")
    Optional<BizesEntity> findByBizesNumber(String bizesNumber);

//    @Query(
//            value = "select b from BizesEntity b where b.bizesName like %"+":bizesName"+"%",
//            countQuery = "select count(b) from BizesEntity b where b.bizesName like %"+":bizesName"+"%"
//    )
    public Page<BizesEntity> findBizesEntityByBizesNameContaining(String bizesName, Pageable pageable);
}
