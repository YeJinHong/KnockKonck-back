package com.test.knockknockback.api.item;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "item")
public class ItemEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "item_number", unique = true, nullable = false)
    private String itemNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "item_image_url")
    private String itemImageUrl;

    @Column(name = "low_price")
    private String lowPrice;

    @Column(name = "high_price")
    private String highPrice;

    @Column(name = "booking_url")
    private String bookingUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bizes_number", referencedColumnName = "bizes_number")
    private BizesEntity bizes;

    @Builder
    public ItemEntity(String itemNumber, String name, String itemImageUrl, String lowPrice, String highPrice, String bookingUrl, BizesEntity bizes){
        this.itemNumber = itemNumber;
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.bizes = bizes;
        this.bookingUrl = bookingUrl;
    }

}
