package com.test.knockknockback.api.crawling;

import com.test.knockknockback.api.booking.BookingDTO;
import com.test.knockknockback.api.booking.BookingState;
import com.test.knockknockback.api.booking.BookingTimeData;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        int hours = 0;
        int diff = 30;
        int minutes = 0;
        for(WebElement we : timeListElement){

            String timeText = we.getText();
            String timeClass = we.getAttribute("class");

            if(timeText.contains("오후")) isAfternoon = true;

            // 시간 정보 - hours 추출
            // H시 => time : "H", H시 30분 => time : ""
            String time = timeText.replaceAll("[^0-9]", "");
            hours = time.isEmpty() ? hours : Integer.parseInt(time);
            if(isAfternoon) hours = (hours%12 + 12); // 12시간제에서 24시간제로 변경

            // 예약 가능 단위 추출
            if(timeClass.contains("half")) {
                diff = timeClass.contains("half") ? 30 : 60;
            }

            // 시간 정보 - minutes 추출
            minutes = time.isEmpty() ? hours*60 + 30 : hours*60;

            BookingState state = timeClass.contains("disabled") ? BookingState.DISABLE : BookingState.ABLE;

            timeDataList.add(
                    BookingTimeData.builder()
                            .hours(hours)
                            .minutes(minutes)
                            .diff(diff)
                            .state(state)
                            .build()
            );
        }
        // 예약 정보가 H시 30분에 시작한 경우 시간정보 보정
        if(!timeDataList.isEmpty() && timeDataList.get(0).getHours() == 12){
            BookingTimeData first = timeDataList.get(0);
            if(timeDataList.size() > 1){
                BookingTimeData second = timeDataList.get(1);
                first.setHours(second.getHours() - 1);
                first.setMinutes(second.getMinutes() - first.getDiff());
            }else {
                // 데이터 추출 시간 기준 예약 가능한 정보가 30분 남은 경우
                // 가정
                // 1. 서버 시간과 크롤링 추출 시간 사이 지연은 60분 미만
                // 2. 크롤링 대상 사이트와 서버의 시간 기준은 동일하다.
                LocalDateTime now = LocalDateTime.now();
                // 지연에 의해 시가 바뀌었을 경우
                if(now.getMinute() < 30){
                    now = now.minusMinutes(30);
                }
                first.setHours(now.getHour());
                first.setMinutes(now.getHour()*60 + 30);
            }
        }


        return BookingDTO.builder()
                .crawlingTimeRequestDTO(crawlingTimeRequestDTO)
                .timeDataList(timeDataList)
                .build();
    }
}
