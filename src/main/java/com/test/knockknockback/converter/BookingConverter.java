package com.test.knockknockback.converter;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.booking.BookingEntity;
import com.test.knockknockback.api.booking.BookingTimeData;
import com.test.knockknockback.api.booking.dto.BookingResponseDTO;
import com.test.knockknockback.api.item.ItemEntity;
import jdk.jfr.Name;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingConverter {

    @Name("E2D")
    @Mapping(target = "hours", source = "booking.hours")
    BookingTimeData toBookingTimeData(BookingEntity booking);

    @IterableMapping(qualifiedByName = "E2D")
    List<BookingTimeData> toBookingTimeDataList(List<BookingEntity> bookingEntityList);

    @Mappings({
           @Mapping(target = "bizesNumber", source ="bizes.bizesNumber"),
           @Mapping(target = "itemNumber", source = "item.itemNumber"),

    })
    BookingResponseDTO toBookingResponseDTO(BizesEntity bizes, ItemEntity item, String startDate, List<BookingTimeData> timeDataList, LocalDateTime lastUpdatedAt);
}
