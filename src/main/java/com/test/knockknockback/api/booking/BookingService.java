package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.bizes.BizesRepsitory;
import com.test.knockknockback.api.booking.dto.BookingRequestDTO;
import com.test.knockknockback.api.booking.dto.BookingResponseDTO;
import com.test.knockknockback.api.item.ItemEntity;
import com.test.knockknockback.api.item.ItemRepository;
import com.test.knockknockback.converter.BookingConverter;
import com.test.knockknockback.util.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.test.knockknockback.util.exception.ErrorCode.BIZES_NOT_EXIST;
import static com.test.knockknockback.util.exception.ErrorCode.ITEM_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BizesRepsitory bizesRepository;
    private final ItemRepository itemRepository;
    private final BookingConverter bookingConverter;

    @Transactional
    public void saveBookingTimeData(BookingDTO bookingDTO){
        BizesEntity bizes = bizesRepository.findByBizesNumber(bookingDTO.getBizesNumber()).orElseThrow(
                () -> CustomException.of(BIZES_NOT_EXIST)
        );
        ItemEntity item = itemRepository.findByItemNumber(bookingDTO.getItemNumber()).orElseThrow(
                () -> CustomException.of(ITEM_NOT_EXIST)
        );
        String startDate = bookingDTO.getStartDate();

        List<BookingEntity> oldBookingEntityList = bookingRepository.findAllByDTO(bizes, item, startDate);
        bookingRepository.deleteAll(oldBookingEntityList);

        for(BookingTimeData timeData : bookingDTO.getTimeDataList()) {
            BookingId bookingId = BookingId.builder()
                    .bizes(bizes)
                    .item(item)
                    .startDate(startDate)
                    .minutes(timeData.getMinutes())
                    .build();

            BookingEntity bookingEntity = BookingEntity.builder()
                    .bookingId(bookingId)
                    .state(timeData.getState())
                    .hours(timeData.getHours())
                    .diff(timeData.getDiff())
                    .build();

            bookingRepository.save(bookingEntity);
        }
    }

    // TODO : BookingDTO-BookingResponseDTO, BookingRequestDTO 구조간 유사성이 높음. 추후 구조 개선 시 고려
    public BookingResponseDTO getBookingData(BookingRequestDTO bookingRequestDTO){
        BizesEntity bizes = bizesRepository.findByBizesNumber(bookingRequestDTO.getBizesNumber()).orElseThrow(
                () -> CustomException.of(BIZES_NOT_EXIST)
        );
        ItemEntity item = itemRepository.findByItemNumber(bookingRequestDTO.getItemNumber()).orElseThrow(
                () -> CustomException.of(ITEM_NOT_EXIST)
        );
        String startDate = bookingRequestDTO.getStartDate();
        List<BookingEntity> bookingEntityList = bookingRepository.findAllByDTO(bizes, item, startDate);

        String lastUpdatedAt = bookingEntityList.isEmpty()?
                null : bookingEntityList.get(0).getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<BookingTimeData> timeDataList = bookingConverter.toBookingTimeDataList(bookingEntityList);

        return bookingConverter.toBookingResponseDTO(bizes, item, startDate, timeDataList, lastUpdatedAt);
    }

}
