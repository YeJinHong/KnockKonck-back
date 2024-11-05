package com.test.knockknockback.api.booking;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "booking")
public class BookingEntity {
    @EmbeddedId
    private BookingId bookingId;

    @Column(name = "is_afternoon")
    private Boolean isAfternoon;

    @Column(name = "is_bookable")
    private Boolean isBookable;

    @Column(name = "type")
    private BookingType type;
}
