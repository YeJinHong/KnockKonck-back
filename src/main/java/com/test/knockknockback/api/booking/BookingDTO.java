package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.crawling.CrawlingTimeRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class BookingDTO {
    private String bizesNumber;
    private String itemNumber;
    private String startDate;
    private List<BookingTimeData> timeDataList;

    @Builder
    private BookingDTO(CrawlingTimeRequestDTO crawlingTimeRequestDTO, List<BookingTimeData> timeDataList){
        this.bizesNumber = crawlingTimeRequestDTO.getBizesNumber();
        this.itemNumber = crawlingTimeRequestDTO.getItemNumber();
        this.startDate = crawlingTimeRequestDTO.getStartDate();
        this.timeDataList = timeDataList;
    }

}
