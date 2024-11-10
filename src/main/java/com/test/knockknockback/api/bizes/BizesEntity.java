package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "bizes")
public class BizesEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "originMapUrl", nullable = false, unique = true)
    private String originMapUrl;

    @Column(name = "place_number", nullable = false, unique = true)
    private String placeNumber;

    @Column(name = "bizes_number", nullable = false, unique = true)
    private String bizesNumber;

    @Column(name = "bizes_image_url", nullable = false)
    private String bizesImageUrl;

    @Column(name = "name", nullable = false, length = 100)
    private String bizesName;

    @Column(name = "address")
    private String address;

    @Builder
    BizesEntity(String originMapUrl, String bizesNumber, String placeNumber, String bizesImageUrl, String bizesName, String address){
        this.originMapUrl = originMapUrl;
        this.bizesImageUrl = bizesImageUrl;
        this.bizesName = bizesName;
        this.bizesNumber = bizesNumber;
        this.address = address;
        this.placeNumber = placeNumber;
    }

}
