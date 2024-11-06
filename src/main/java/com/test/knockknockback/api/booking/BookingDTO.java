package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.crawling.CrawlingTimeRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookingDTO {
    private String bizesNumber;
    private String itemNumber;
    private String startDate;
    private Integer hours;
    private BookingType type;

    @Builder
    private BookingDTO(CrawlingTimeRequestDTO crawlingTimeRequestDTO, int hours, BookingType type){
        this.bizesNumber = crawlingTimeRequestDTO.getBizesNumber();
        this.itemNumber = crawlingTimeRequestDTO.getItemNumber();
        this.startDate = crawlingTimeRequestDTO.getStartDate();
        this.hours = hours;
        this.type = type;
    }

}
