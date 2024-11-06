package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.bizes.dto.BizesRegisterRequestDTO;
import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.crawling.CrawlingService;
import com.test.knockknockback.api.item.ItemService;
import com.test.knockknockback.converter.BizesConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bizes")
public class BizesController {

    private final BizesService bizesService;
    private final BizesConverter bizesConverter;
    private final CrawlingService crawlingService;
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<?> findBizesByMapUrl(String mapUrl){
        BizesResponseDTO bizesResponseDTO = bizesService.findBizesByMapUrl(mapUrl);
        return ResponseEntity.ok().body(bizesResponseDTO);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerBizes(
            @RequestBody BizesRegisterRequestDTO bizesRegisterRequestDTO
    ){
        String mapUrl = bizesRegisterRequestDTO.getMapUrl();
        BizesItemSO bizesItemSO = crawlingService.crawlingBizesItemList(mapUrl);
        bizesService.registerBizes(bizesItemSO);
        itemService.registerItemList(bizesItemSO.getBizesNumber(), bizesItemSO.getItemList());
        return ResponseEntity.ok().body(
                bizesConverter.toBizesRegisterResponseDTO(mapUrl, bizesItemSO)
        );
    }
}
