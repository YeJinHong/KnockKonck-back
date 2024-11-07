package com.test.knockknockback.converter;

import com.test.knockknockback.api.subscribe.SubscribeEntity;
import com.test.knockknockback.api.subscribe.dto.SubscribeResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscribeConverter {
    @Named("E2D")
    @Mappings({
            @Mapping(source = "userName", target = "subscribe.subscribeId.userName"),
            @Mapping(source = "bizesNumber", target = "subscribe.subscribeId.bizesNumber"),
            @Mapping(source = "itemNumber", target = "subscribe.subscribeId.itemNumber"),
    })
    SubscribeResponseDTO toSubscribeDTO(SubscribeEntity subscribe);
    @IterableMapping(qualifiedByName = "E2D")
    List<SubscribeResponseDTO> toSubscribeDTOList(List<SubscribeEntity> subscribeEntityList);
}
