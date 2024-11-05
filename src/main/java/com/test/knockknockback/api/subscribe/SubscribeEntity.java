package com.test.knockknockback.api.subscribe;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "subscribe")
public class SubscribeEntity {
    @EmbeddedId
    private SubscribeId subscribeId;

    private String originMapUrl;
    @Builder
    SubscribeEntity(String placeNumber, String userName, String originMapUrl){
        this.subscribeId = SubscribeId.builder()
                .placeNumber(placeNumber)
                .userName(userName)
                .build();
        this.originMapUrl = originMapUrl;
    }

}
