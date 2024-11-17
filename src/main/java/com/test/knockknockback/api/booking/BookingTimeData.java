package com.test.knockknockback.api.booking;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookingTimeData {
    private int hours;
    private int minutes;
    private int diff;
    private BookingState state;
}
