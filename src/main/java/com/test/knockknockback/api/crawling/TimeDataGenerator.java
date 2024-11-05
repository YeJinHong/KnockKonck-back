package com.test.knockknockback.api.crawling;

import com.test.knockknockback.api.booking.BookingDTO;
import com.test.knockknockback.api.booking.BookingType;
import com.test.knockknockback.converter.BookingConverter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TimeDataGenerator {

    public List<BookingDTO> generateBookingList(
            CrawlingTimeRequestDTO crawlingTimeRequestDTO,
            List<WebElement> timeListElement
    ){
        List<BookingDTO> bookingList = new ArrayList<>();

        boolean isAfternoon = false;
        for(WebElement we : timeListElement){
            String timeText = we.getText();
            String timeClass = we.getAttribute("class");
            if(timeText.contains("오후")) isAfternoon = true;

            int hour = Integer.parseInt(timeText.replaceAll("[^0-9]", ""));
            BookingType type = timeClass.contains("half") ? BookingType.HALF : BookingType.FULL;
            Boolean isBookable = !timeClass.contains("disabled");

            bookingList.add(
                    BookingDTO.builder()
                            .crawlingTimeRequestDTO(crawlingTimeRequestDTO)
                            .hour(hour)
                            .isAfternoon(isAfternoon)
                            .isBookable(isBookable)
                            .type(type)
                            .build()
            );

        }
        return bookingList;
    }
}
