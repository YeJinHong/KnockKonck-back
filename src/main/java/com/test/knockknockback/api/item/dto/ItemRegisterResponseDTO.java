package com.test.knockknockback.api.item.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRegisterResponseDTO {
    private String itemNumber;
    private String ItemName;
    private String itemImageUrl;
    private String bookingUrl;
}
