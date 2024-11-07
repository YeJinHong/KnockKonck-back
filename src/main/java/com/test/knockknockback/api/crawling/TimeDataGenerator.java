package com.test.knockknockback.api.crawling;

import com.test.knockknockback.api.booking.BookingDTO;
import com.test.knockknockback.api.booking.BookingTimeData;
import com.test.knockknockback.api.booking.BookingType;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TimeDataGenerator {

    public BookingDTO generateBookingTimeData(
            CrawlingTimeRequestDTO crawlingTimeRequestDTO,
            List<WebElement> timeListElement
    ){
        List<BookingTimeData> timeDataList = new ArrayList<>();
        boolean isAfternoon = false;
        for(WebElement we : timeListElement){

            String timeText = we.getText();
            String timeClass = we.getAttribute("class");
            if(timeText.contains("오후")) isAfternoon = true;

            if(!timeClass.contains("disabled")) {
                continue;
            }
            int hours = Integer.parseInt(timeText.replaceAll("[^0-9]", ""));
            if(isAfternoon) hours = (hours%12 + 12); // 12시간제에서 24시간제로 변경
            BookingType type = timeClass.contains("half") ? BookingType.HALF : BookingType.FULL;

            timeDataList.add(
                    BookingTimeData.builder()
                            .hours(hours)
                            .type(type)
                            .build()
            );

        }
        return BookingDTO.builder()
                .crawlingTimeRequestDTO(crawlingTimeRequestDTO)
                .timeDataList(timeDataList)
                .build();
    }
}
