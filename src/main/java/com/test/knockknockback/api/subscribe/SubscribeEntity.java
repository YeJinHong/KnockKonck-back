package com.test.knockknockback.api.subscribe;

import com.test.knockknockback.api.bizes.BizesEntity;
import com.test.knockknockback.api.common.BaseEntity;
import com.test.knockknockback.api.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

@Getter
@Entity
@NoArgsConstructor
@Table(
        name = "subscribe",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"bizes_number", "itemNumber", "userName"})}
)
public class SubscribeEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

//    @EmbeddedId
    @Column(unique = true)
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
