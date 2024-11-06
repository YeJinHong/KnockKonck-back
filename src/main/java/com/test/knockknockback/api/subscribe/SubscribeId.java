package com.test.knockknockback.api.subscribe;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.item.ItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class SubscribeId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bizes_number")
    private BizesEntity bizes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_number")
    private ItemEntity item;

    @Column(name = "user_name")
    private String userName;
    @Builder
    SubscribeId(BizesEntity bizes, ItemEntity item, String userName){
        this.bizes = bizes;
        this.item = item;
        this.userName = userName;
    }
}
