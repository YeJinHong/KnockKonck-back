package com.test.knockknockback.api.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemSO {
    private String bizesNumber;
    private String itemNumber;
    private String name;
    private String itemImageUrl;
    private String lowPrice;
    private String highPrice;
    private String bookingUrl;
}
