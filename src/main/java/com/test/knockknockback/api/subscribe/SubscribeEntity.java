package com.test.knockknockback.api.subscribe;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.common.BaseEntity;
import com.test.knockknockback.api.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "subscribe")
public class SubscribeEntity extends BaseEntity {

    @GeneratedValue
    @Column(unique = true)
    private Long id;

    @EmbeddedId
    private SubscribeId subscribeId;
    @Builder
    SubscribeEntity(BizesEntity bizes, ItemEntity item, String userName){
        this.subscribeId = SubscribeId.builder()
                .bizes(bizes)
                .item(item)
                .userName(userName)
                .build();
    }

}
