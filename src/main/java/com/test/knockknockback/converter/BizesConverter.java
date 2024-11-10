package com.test.knockknockback.converter;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.bizes.BizesItemSO;
import com.test.knockknockback.api.bizes.dto.BizesRegisterResponseDTO;
import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.item.ItemSO;
import com.test.knockknockback.api.item.dto.ItemRegisterResponseDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BizesConverter {
    BizesResponseDTO toBizesResponseDTO(BizesEntity bizes);

    @Mappings({
            @Mapping(source = "itemNumber", target = "itemNumber"),
            @Mapping(source = "name", target = "itemName"),
            @Mapping(source = "bookingUrl", target = "bookingUrl"),
    })
    ItemRegisterResponseDTO toItemRegisterResponseDTO(ItemSO itemSO);

    @Mappings({
            @Mapping(source ="mapUrl", target = "originMapUrl"),
            @Mapping(source = "bizesItemSO.itemList", target="itemList")
    })
    BizesRegisterResponseDTO toBizesRegisterResponseDTO(String mapUrl, BizesItemSO bizesItemSO);
}
