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
        String startDate = bookingDTO.getStartDate();

        List<BookingEntity> oldBookingEntityList = bookingRepository.findAllByDTO(bizes, item, startDate);
        bookingRepository.deleteAll(oldBookingEntityList);

        for(BookingTimeData timeData : bookingDTO.getTimeDataList()) {
            BookingId bookingId = BookingId.builder()
                    .bizes(bizes)
                    .item(item)
                    .startDate(startDate)
                    .hours(timeData.getHours())
                    .build();

            BookingEntity bookingEntity = BookingEntity.builder()
                    .bookingId(bookingId)
                    .type(timeData.getType())
                    .build();

            bookingRepository.save(bookingEntity);
        }
    }

}
