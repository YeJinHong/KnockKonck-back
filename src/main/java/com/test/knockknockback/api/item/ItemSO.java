package com.test.knockknockback.api.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemSO {
    private String bizesNumber;
    private String itemNumber;
    private String name;
    private Integer lowPrice;
    private Integer highPrice;
    private String bookingUrl;
}
