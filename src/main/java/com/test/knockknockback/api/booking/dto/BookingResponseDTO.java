package com.test.knockknockback.api.booking.dto;

import com.test.knockknockback.api.booking.BookingTimeData;
import com.test.knockknockback.api.crawling.CrawlingTimeRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@ToString
@NoArgsConstructor
public class BookingResponseDTO {
    private String bizesNumber;
    private String itemNumber;
    private String startDate;
    private String lastUpdatedAt;
    private List<BookingTimeData> timeDataList;

    @Builder
    public BookingResponseDTO(String bizesNumber, String itemNumber, String startDate, String lastUpdatedAt, List<BookingTimeData> timeDataList) {
        this.bizesNumber = bizesNumber;
        this.itemNumber = itemNumber;
        this.startDate = startDate;
        this.lastUpdatedAt = lastUpdatedAt;
        this.timeDataList = timeDataList;
    }
}
