package com.test.knockknockback.api.item.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDTO {
    private String bizesNumber;
    private String address;
    private String itemNumber;
    private String itemImageUrl;
    private String itemName;
    private String lowPrice;
    private String highPrice;
    private String bookingUrl;
}
