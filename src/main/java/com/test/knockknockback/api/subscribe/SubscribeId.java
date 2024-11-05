package com.test.knockknockback.api.subscribe;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class SubscribeId implements Serializable {
    @Column(name = "bizes_number")
    private String bizesNumber;
    @Column(name = "user_name")
    private String userName;

    @Builder
    public SubscribeId(String bizesNumber, String userName){
        this.bizesNumber = bizesNumber;
        this.userName = userName;
    }
}
