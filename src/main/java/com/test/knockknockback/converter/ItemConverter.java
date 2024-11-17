package com.test.knockknockback.converter;

import com.test.knockknockback.api.item.ItemEntity;
import com.test.knockknockback.api.item.dto.ItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ItemConverter {
    @Mappings({
            @Mapping(target = "bizesName", source = "item.bizes.bizesName"),
            @Mapping(target = "bizesNumber", source = "item.bizes.bizesNumber"),
            @Mapping(target = "address", source = "item.bizes.address"),
            @Mapping(target = "itemName", source = "item.name")
    })
    ItemResponseDTO toItemResponseDTO(ItemEntity item);
}
