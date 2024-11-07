package com.test.knockknockback.api.booking;

import com.test.knockknockback.api.booking.dto.BookingRequestDTO;
import com.test.knockknockback.api.booking.dto.BookingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping()
    public BookingResponseDTO getBookingData(
            @RequestParam(name = "bizesNumber") String bizesNumber,
            @RequestParam(name = "itemNumber") String itemNumber,
            @RequestParam(name = "startDate") String startDate
    ){
        // TODO : Arguement Resolver 적용으로 변경
        BookingRequestDTO bookingRequestDTO = BookingRequestDTO.builder()
                .bizesNumber(bizesNumber)
                .itemNumber(itemNumber)
                .startDate(startDate)
                .build();
        return bookingService.getBookingData(bookingRequestDTO);
    }
}
