package com.test.knockknockback.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlModifier {
    public static String removeQueryString(String origin) {
        return origin.contains("?") ? origin.substring(0, origin.indexOf("?")) : origin;
    }
    public static String getBookingUrl(String mapUrl){
        return removeQueryString(mapUrl) + "?placePath=/ticket";
    }
}
