package com.test.knockknockback.api.subscribe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;

    public void registerSubs(SubscribeDTO subscribeDTO){
        subscribeRepository.save(
                SubscribeEntity.builder()
                        .userName(subscribeDTO.getUserName())
                        .originMapUrl(subscribeDTO.getOriginMapUrl())
                        .placeNumber(subscribeDTO.getPlaceNumber())
                        .build()
        );
    }
}
