package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.common.BaseEntity;
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
public class BookingEntity extends BaseEntity {
    @EmbeddedId
    private BookingId bookingId;

    @Column(name = "type")
    private BookingType type;
}
