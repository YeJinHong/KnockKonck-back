package com.test.knockknockback.api.bizes.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BizesResponseDTO {
    private String originMapUrl;
    private String bizesNumber;
    private String placeNumber;
    private String bizesName;
    private String address;
}
