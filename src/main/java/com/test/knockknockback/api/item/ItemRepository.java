package com.test.knockknockback.api.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    @Query("select i.bookingUrl from ItemEntity i where i.bizes.bizesNumber = :bizesNumber and i.itemNumber = :itemNumber")
    String findBookingUrlByBizesNumberAndItemNumber(String bizesNumber, String itemNumber);

    @Query("select i from ItemEntity i where i.itemNumber = :itemNumber")
    Optional<ItemEntity> findByItemNumber(String itemNumber);

    @Query("select i from ItemEntity i where i.bizes.bizesNumber = :bizesNumber")
    List<ItemEntity> findByBizesNumber(String bizesNumber);
}
