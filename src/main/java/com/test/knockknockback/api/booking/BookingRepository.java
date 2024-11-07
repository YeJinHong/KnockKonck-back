package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, BookingId> {

    @Query("select b from BookingEntity b where b.bookingId.bizes = :bizes and b.bookingId.item = :item and b.bookingId.startDate = :startDate")
    List<BookingEntity> findAllByDTO(BizesEntity bizes, ItemEntity item, String startDate);
}
