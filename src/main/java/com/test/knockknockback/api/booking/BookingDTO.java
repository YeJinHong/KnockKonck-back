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
    private Boolean isAfternoon;
    private Boolean isBookable;
    private BookingType type;

    @Builder
    private BookingDTO(CrawlingTimeRequestDTO crawlingTimeRequestDTO, int hours, Boolean isAfternoon, Boolean isBookable, BookingType type){
        this.bizesNumber = crawlingTimeRequestDTO.getBizesNumber();
        this.itemNumber = crawlingTimeRequestDTO.getItemNumber();
        this.startDate = crawlingTimeRequestDTO.getStartDate();
        this.hours = hours;
        this.isAfternoon = isAfternoon;
        this.isBookable = isBookable;
        this.type = type;
    }

}
