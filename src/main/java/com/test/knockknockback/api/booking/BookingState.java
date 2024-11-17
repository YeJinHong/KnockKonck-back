package com.test.knockknockback.api.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingState {
    ABLE("able"),
    DISABLE("disable");

    private String description;
}
