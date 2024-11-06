package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class BookingId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bizes_number")
    private BizesEntity bizes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_number")
    private ItemEntity item;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "hours")
    private Integer hours;
}
