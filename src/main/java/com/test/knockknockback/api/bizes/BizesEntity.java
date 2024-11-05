package com.test.knockknockback.api.bizes;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "bizes")
public class BizesEntity {

    @Id
    @Column(name = "bizes_number", nullable = false, unique = true)
    private String bizesNumber;

    @Column(name = "place_number", nullable = false, unique = true)
    private String placeNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String bizesName;

    @Column(name = "address")
    private String address;

    @Builder
    BizesEntity(String bizesNumber, String placeNumber, String bizesName, String address){
        this.bizesName = bizesName;
        this.bizesNumber = bizesNumber;
        this.address = address;
        this.placeNumber = placeNumber;
    }

}
