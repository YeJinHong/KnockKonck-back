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
    private BookingType type;

    @Builder
    private BookingEntity(BookingId bookingId, BookingType type){
        this.bookingId = bookingId;
        this.type = type;
    }
}
