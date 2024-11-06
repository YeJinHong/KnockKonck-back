package com.test.knockknockback.api.crawling;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.StringTokenizer;

@NoArgsConstructor
@Component
public class UrlParamExtractor {

    private static final String BIZES = "bizes";
    private static final String ITEMS = "items";
    private static final String PLACE = "place";

    public static String getParamFromUrl(String url, String paramName){
        StringTokenizer st = new StringTokenizer(url.substring(url.indexOf(paramName)), "[/?=&]");
        st.nextToken();
        return st.nextToken();
    }

    public static String getPlaceNumberFromUrl(String url){
        return getParamFromUrl(url, PLACE);
    }

    public static String getBizesNumberFromUrl(String url){
        return getParamFromUrl(url, BIZES);
    }
    public static String getItemNumberFromUrl(String url){
        return getParamFromUrl(url, ITEMS);
    }
}
