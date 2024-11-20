package com.test.knockknockback.api.item.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRegisterResponseDTO {
    private String itemNumber;
    private String itemName;
    private String itemImageUrl;
    private String bookingUrl;
}
