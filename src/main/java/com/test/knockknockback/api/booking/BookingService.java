package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.bizes.BizesRepsitory;
import com.test.knockknockback.api.item.ItemEntity;
import com.test.knockknockback.api.item.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BizesRepsitory bizesRepsitory;
    private final ItemRepository itemRepository;
    @Transactional
    public void saveBookingTimeData(BookingDTO bookingDTO){
        BizesEntity bizes = bizesRepsitory.findByBizesNumber(bookingDTO.getBizesNumber()).orElse(null);
        ItemEntity item = itemRepository.findByItemNumber(bookingDTO.getItemNumber()).orElse(null);

        BookingId bookingId = BookingId.builder()
                .bizes(bizes)
                .item(item)
                .startDate(bookingDTO.getStartDate())
                .hours(bookingDTO.getHours())
                .build();

        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingId(bookingId)
                .type(bookingDTO.getType())
                .build();

        List<BookingEntity> oldBookingData = bookingRepository.findAllByDTO(bizes, item, bookingDTO.getStartDate());
        bookingRepository.deleteAll(oldBookingData);
        bookingRepository.save(bookingEntity);
    }
}
