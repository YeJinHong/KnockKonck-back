package com.test.knockknockback.api.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingType {
    HALF("half"),
    FULL("full");

    private String description;
}
