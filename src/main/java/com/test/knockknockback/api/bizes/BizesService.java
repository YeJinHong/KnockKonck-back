package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.crawling.UrlParamExtractor;
import com.test.knockknockback.converter.BizesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BizesService {

    private final BizesRepsitory bizesRepsitory;
    private final BizesConverter bizesConverter;

    public BizesResponseDTO findBizesByMapUrl(String mapUrl){
        String placeNumber = UrlParamExtractor.getPlaceNumberFromUrl(mapUrl);
        BizesEntity bizesEntity = bizesRepsitory.findByPlaceNumber(placeNumber).orElse(null);
        return bizesConverter.toBizesResponseDTO(bizesEntity);
    }

    public Page<BizesResponseDTO> findBizes(String bizesName, Pageable pageable){

        System.out.println("bizesName : "+bizesName);
        Page<BizesEntity> bizesEntities = bizesRepsitory.findBizesEntityByBizesNameContaining(bizesName, pageable);
        System.out.println(bizesEntities.getSize());
        System.out.println(bizesEntities.getContent());
        return bizesEntities.map(bizesConverter::toBizesResponseDTO);
    }

    public void registerBizes(BizesItemSO bizesItemSO){
        BizesEntity bizesEntity = BizesEntity.builder()
                .originMapUrl(bizesItemSO.getOriginMapUrl())
                .placeNumber(bizesItemSO.getPlaceNumber())
                .bizesNumber(bizesItemSO.getBizesNumber())
                .bizesName(bizesItemSO.getBizesName())
                .address(bizesItemSO.getAddress())
                .build();

        bizesRepsitory.save(bizesEntity);
    }
}
