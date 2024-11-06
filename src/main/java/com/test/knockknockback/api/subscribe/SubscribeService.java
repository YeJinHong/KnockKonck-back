package com.test.knockknockback.api.subscribe;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.bizes.BizesRepsitory;
import com.test.knockknockback.api.item.ItemEntity;
import com.test.knockknockback.api.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final BizesRepsitory bizesRepository;
    private final ItemRepository itemRepository;

    public void registerSubs(SubscribeDTO subscribeDTO){
        BizesEntity bizes = bizesRepository.findByBizesNumber(subscribeDTO.getBizesNumber()).orElse(null);
        ItemEntity item = itemRepository.findByItemNumber(subscribeDTO.getItemNumber()).orElse(null);

        subscribeRepository.save(
                SubscribeEntity.builder()
                        .userName(subscribeDTO.getUserName())
                        .bizes(bizes)
                        .item(item)
                        .build()
        );
    }
}
