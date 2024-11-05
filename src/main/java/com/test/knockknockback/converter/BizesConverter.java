package com.test.knockknockback.converter;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BizesConverter {
    BizesResponseDTO toBizesResponseDTO(BizesEntity bizesEntity);
}
