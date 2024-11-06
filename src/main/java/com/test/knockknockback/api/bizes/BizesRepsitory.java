package com.test.knockknockback.api.bizes;

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
}
