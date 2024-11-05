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
    @Column(name = "place_number")
    private String placeNumber;
    @Column(name = "user_name")
    private String userName;
    @Builder
    SubscribeId(String placeNumber, String userName){
        this.placeNumber = placeNumber;
        this.userName = userName;
    }
}
