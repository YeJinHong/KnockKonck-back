package com.test.knockknockback.api.item;

import com.test.knockknockback.api.bizes.BizesEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "item")
public class ItemEntity {

    @Id
    @Column(name = "item_number", nullable = false)
    private String itemNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "low_price")
    private Integer lowPrice;

    @Column(name = "high_price")
    private Integer highPrice;

    @Column(name = "booking_url")
    private String bookingUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bizes_number", referencedColumnName = "bizes_number")
    private BizesEntity bizes;

    @Builder
    public ItemEntity(String itemNumber, String name, Integer lowPrice, Integer highPrice, String bookingUrl, BizesEntity bizes){
        this.itemNumber = itemNumber;
        this.name = name;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.bizes = bizes;
        this.bookingUrl = bookingUrl;
    }
}
