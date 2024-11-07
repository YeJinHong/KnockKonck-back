package com.test.knockknockback.api.booking.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BookingRequestDTO {
    private String bizesNumber;
    private String itemNumber;
    private String startDate;
}
