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

    @Builder
    public SubscribeEntity(String bizesNumber, String userName){
        this.subscribeId = SubscribeId.builder()
                .bizesNumber(bizesNumber)
                .userName(userName).build();
    }
}
