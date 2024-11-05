package com.test.knockknockback.api.subscribe;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class SubscribeDTO {
    private String userName;
    private String originMapUrl;
    private String placeNumber;
}
