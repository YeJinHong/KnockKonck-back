package com.test.knockknockback.converter;

import com.test.knockknockback.api.subscribe.SubscribeEntity;
import com.test.knockknockback.api.subscribe.dto.SubscribeResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscribeConverter {
    @Named("E2D")
    @Mappings({
            @Mapping(target = "userName", source = "subscribe.subscribeId.userName"),
            @Mapping(target = "bizesNumber", source = "subscribe.subscribeId.bizes.bizesNumber"),
            @Mapping(target = "itemNumber", source = "subscribe.subscribeId.item.itemNumber"),
    })
    SubscribeResponseDTO toSubscribeDTO(SubscribeEntity subscribe);
    @IterableMapping(qualifiedByName = "E2D")
    List<SubscribeResponseDTO> toSubscribeDTOList(List<SubscribeEntity> subscribeEntityList);
}
