package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.crawling.UrlParamExtractor;
import com.test.knockknockback.api.item.ItemEntity;
import com.test.knockknockback.api.item.ItemRepository;
import com.test.knockknockback.converter.BizesConverter;
import com.test.knockknockback.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.test.knockknockback.util.exception.ErrorCode.BIZES_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class BizesService {

    private final BizesRepsitory bizesRepsitory;
    private final BizesConverter bizesConverter;
    private final ItemRepository itemRepository;

    public BizesResponseDTO findBizesByMapUrl(String mapUrl){
        String placeNumber = UrlParamExtractor.getPlaceNumberFromUrl(mapUrl);
        BizesEntity bizesEntity = bizesRepsitory.findByPlaceNumber(placeNumber).orElseThrow(
                () -> CustomException.of(BIZES_NOT_EXIST)
        );

        BizesResponseDTO bizesResponseDTO = bizesConverter.toBizesResponseDTO(bizesEntity);
        List<ItemEntity> itemEntityList =  itemRepository.findByBizesNumber(bizesEntity.getBizesNumber());
        bizesResponseDTO.setItemList(bizesConverter.toItemRegisterResponseDTOList(itemEntityList));

        return bizesResponseDTO;
    }

    public Page<BizesResponseDTO> findBizes(String bizesName, Pageable pageable){

        Page<BizesEntity> bizesEntities = bizesRepsitory.findBizesEntityByBizesNameContaining(bizesName, pageable);
        return bizesEntities.map(bizesConverter::toBizesResponseDTO);
    }

    public void registerBizes(BizesItemSO bizesItemSO){
        BizesEntity bizesEntity = BizesEntity.builder()
                .originMapUrl(bizesItemSO.getOriginMapUrl())
                .placeNumber(bizesItemSO.getPlaceNumber())
                .bizesImageUrl(bizesItemSO.getBizesImageUrl())
                .bizesNumber(bizesItemSO.getBizesNumber())
                .bizesName(bizesItemSO.getBizesName())
                .address(bizesItemSO.getAddress())
                .build();

        bizesRepsitory.save(bizesEntity);
    }
}
