package com.test.knockknockback.api.booking;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingTimeData {
    private Integer hours;
    private BookingType type;
}
