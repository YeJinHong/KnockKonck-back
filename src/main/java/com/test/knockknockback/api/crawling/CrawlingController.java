package com.test.knockknockback.api.crawling;

import com.test.knockknockback.api.booking.BookingDTO;
import com.test.knockknockback.api.booking.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/crawling")
public class CrawlingController {

    private final CrawlingService crawlingService;
    private final BookingService bookingService;

    // 크롤링 정상 동작 확인 완료
    @PatchMapping("/time-data")
    public ResponseEntity<?> crawlingItemTimeData(
            @RequestBody CrawlingTimeRequestDTO crawlingTimeRequestDTO
    ){
        // TODO : 최근 업데이트 시간 비교

        BookingDTO bookingDTO = crawlingService.getBookingTimeData(crawlingTimeRequestDTO);
        bookingService.saveBookingTimeData(bookingDTO);

        return null;
    }

    
}

