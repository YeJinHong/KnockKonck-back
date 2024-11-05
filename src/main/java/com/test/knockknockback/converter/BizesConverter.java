package com.test.knockknockback.converter;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BizesConverter {
    @Mapping(source = "mapUrl", target = "originMapUrl")
    BizesResponseDTO toBizesResponseDTO(String mapUrl, BizesEntity bizesEntity);
}
