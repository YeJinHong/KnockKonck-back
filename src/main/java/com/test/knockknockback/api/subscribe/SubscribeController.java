package com.test.knockknockback.api.subscribe;

import com.test.knockknockback.api.bizes.BizesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subs")
public class SubscribeController {

    private final SubscribeService subscribeService;

    private final BizesService bizesService;
    @PostMapping()
    public ResponseEntity<?> registerSubs(
            @RequestBody SubscribeDTO subscribeDTO
    ) {
        subscribeService.registerSubs(subscribeDTO);
//      bize정보가 없다면 등록한다.
//        bizesService.registerBizes(subscribeDTO);
        return null;
    }

}
