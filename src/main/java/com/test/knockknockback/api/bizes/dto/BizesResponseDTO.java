package com.test.knockknockback.api.bizes.dto;

import com.test.knockknockback.api.item.dto.ItemRegisterResponseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BizesResponseDTO {
    private String originMapUrl;
    private String placeNumber;
    private String bizesNumber;
    private String bizesImageUrl;
    private String bizesName;
    private String address;
    private List<ItemRegisterResponseDTO> itemList;
}
