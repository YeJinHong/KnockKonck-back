package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.crawling.CrawlingService;
import com.test.knockknockback.api.crawling.UrlParamExtractor;
import com.test.knockknockback.api.item.ItemEntity;
import com.test.knockknockback.api.item.ItemRepository;
import com.test.knockknockback.api.item.ItemSO;
import com.test.knockknockback.api.subscribe.SubscribeDTO;
import com.test.knockknockback.converter.BizesConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BizesService {

    private final BizesRepsitory bizesRepsitory;
    private final BizesConverter bizesConverter;

    private final CrawlingService crawlingService;

    private final ItemRepository itemRepository;

    private final String PLACE = "place";

    public BizesResponseDTO findBizesByMapUrl(String mapUrl){
        String placeNumber = UrlParamExtractor.getParamFromUrl(mapUrl, PLACE);
        BizesEntity bizesEntity = bizesRepsitory.findByPlaceNumber(placeNumber).orElse(null);
        return bizesConverter.toBizesResponseDTO(bizesEntity);
    }
    @Transactional
    public void registerBizes(SubscribeDTO subscribeDTO){
        String userName = subscribeDTO.getUserName();
        System.out.println(subscribeDTO);

        BizesItemSO bizesItemSO = crawlingService.crawlingBizesItemList(subscribeDTO.getOriginMapUrl());

        System.out.println("bizesItem : "+bizesItemSO);

        // TODO : bizes 정보가 이미 있는 경우 재등록 X
        BizesEntity bizesEntity = BizesEntity.builder()
                .bizesNumber(bizesItemSO.getBizesNumber())
                .bizesName(bizesItemSO.getBizesName())
                .address(bizesItemSO.getAddress())
                .build();

        bizesRepsitory.save(bizesEntity);

        System.out.println("bizes 저장 완료");

        System.out.println("itemSO save");

        for(ItemSO itemSO : bizesItemSO.getItemList()) {
            itemRepository.save(
                    ItemEntity.builder()
                            .itemNumber(itemSO.getItemNumber())
                            .name(itemSO.getName())
                            .lowPrice(itemSO.getLowPrice())
                            .highPrice(itemSO.getHighPrice())
                            .bookingUrl(itemSO.getBookingUrl())
                            .bizes(bizesEntity)
                            .build()
            );
        }

    }
}
