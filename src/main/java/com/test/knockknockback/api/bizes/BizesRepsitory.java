package com.test.knockknockback.api.bizes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BizesRepsitory extends JpaRepository<BizesEntity, String> {
    @Query("select b from bizes b where b.place_number = :placeNumber")
    Optional<BizesEntity> findByPlaceNumber(String placeNumber);
}
