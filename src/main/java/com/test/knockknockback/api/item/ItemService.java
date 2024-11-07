package com.test.knockknockback.api.item;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.bizes.BizesRepsitory;
import com.test.knockknockback.api.item.dto.ItemResponseDTO;
import com.test.knockknockback.converter.ItemConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final BizesRepsitory bizesRepsitory;
    private final ItemConverter itemConverter;
    @Transactional
    public void registerItemList(String bizesNumber, List<ItemSO> itemSOList){
        BizesEntity bizesEntity = bizesRepsitory.findByBizesNumber(bizesNumber).orElse(null);
        for(ItemSO itemSO : itemSOList) {
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

    public List<ItemResponseDTO> getItemInfoList(List<String> itemNumberList){
        List<ItemResponseDTO> itemInfoList = new ArrayList<>();
        for(String itemNumber : itemNumberList){
            ItemEntity item = itemRepository.findByItemNumber(itemNumber).orElse(null);
            itemInfoList.add(itemConverter.toItemResponseDTO(item));
        }
        return itemInfoList;
    }


}
