package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.crawling.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bizes")
public class BizesController {

    private final BizesService bizesService;
    @GetMapping
    public ResponseEntity<?> findBizesByMapUrl(String mapUrl){
        BizesResponseDTO bizesResponseDTO = bizesService.findBizesByMapUrl(mapUrl);
        return ResponseEntity.ok().body(bizesResponseDTO);
    }

}
