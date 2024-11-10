package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.bizes.dto.BizesRegisterRequestDTO;
import com.test.knockknockback.api.bizes.dto.BizesResponseDTO;
import com.test.knockknockback.api.crawling.CrawlingService;
import com.test.knockknockback.api.item.ItemService;
import com.test.knockknockback.converter.BizesConverter;
import com.test.knockknockback.util.UrlModifier;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    // TODO : 이유없이 bizesName이 빈 문자열로 받아지는 경우가 있음
    @GetMapping
    public ResponseEntity<?> findBizes(
            @RequestParam(name = "mapUrl", required = false) String mapUrl,
            @RequestParam(name = "bizesName", required = false) String bizesName,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        // TODO : ArgumentResolver 혹은 validation 처리
        if(mapUrl != null) {
            BizesResponseDTO bizesResponseDTO = bizesService.findBizesByMapUrl(mapUrl);
            return ResponseEntity.ok().body(bizesResponseDTO);
        } else {
            Page<BizesResponseDTO> bizesList = bizesService.findBizes(bizesName, pageable);
            return ResponseEntity.ok().body(bizesList);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerBizes(
            @RequestBody BizesRegisterRequestDTO bizesRegisterRequestDTO
    ){
        String mapUrl = UrlModifier.removeQueryString(bizesRegisterRequestDTO.getMapUrl());
        BizesItemSO bizesItemSO = crawlingService.crawlingBizesItemList(mapUrl);
        bizesService.registerBizes(bizesItemSO);
        itemService.registerItemList(bizesItemSO.getBizesNumber(), bizesItemSO.getItemList());
        return ResponseEntity.ok().body(
                bizesConverter.toBizesRegisterResponseDTO(mapUrl, bizesItemSO)
        );
    }
}
