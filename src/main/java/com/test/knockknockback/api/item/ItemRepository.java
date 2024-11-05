package com.test.knockknockback.api.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    @Query("select i.bookingUrl from ItemEntity i where i.itemNumber = :itemNumber")
    String findBookingUrlByItemNumber(String itemNumber);
}
