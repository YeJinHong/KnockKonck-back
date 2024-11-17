package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "booking")
public class BookingEntity extends BaseEntity {
    @EmbeddedId
    private BookingId bookingId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BookingState state;

    @Column(name = "hours")
    private int hours;

    @Column(name = "diff")
    private int diff;

    @Builder
    private BookingEntity(BookingId bookingId, BookingState state, int hours, int diff){
        this.bookingId = bookingId;
        this.state = state;
        this.hours = hours;
        this.diff = diff;
    }
}
