package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.crawling.UrlParamExtractor;
import com.test.knockknockback.converter.BizesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BizesService {

    private final BizesRepsitory bizesRepsitory;
    private final BizesConverter bizesConverter;

    public BizesResponseDTO findBizesByMapUrl(String mapUrl){
        String placeNumber = UrlParamExtractor.getPlaceNumberFromUrl(mapUrl);
        BizesEntity bizesEntity = bizesRepsitory.findByPlaceNumber(placeNumber).orElse(null);
        return bizesConverter.toBizesResponseDTO(mapUrl, bizesEntity);
    }
    public void registerBizes(BizesItemSO bizesItemSO){
        BizesEntity bizesEntity = BizesEntity.builder()
                .placeNumber(bizesItemSO.getPlaceNumber())
                .bizesNumber(bizesItemSO.getBizesNumber())
                .bizesName(bizesItemSO.getBizesName())
                .address(bizesItemSO.getAddress())
                .build();

        bizesRepsitory.save(bizesEntity);
    }
}
