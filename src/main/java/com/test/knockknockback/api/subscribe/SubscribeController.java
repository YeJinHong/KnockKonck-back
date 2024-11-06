package com.test.knockknockback.api.subscribe;

import com.test.knockknockback.api.bizes.BizesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subs")
public class SubscribeController {

    private final SubscribeService subscribeService;

    @PostMapping()
    public ResponseEntity<?> registerSubs(
            @RequestBody SubscribeDTO subscribeDTO
    ) {
        subscribeService.registerSubs(subscribeDTO);
        return null;
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<?> unsubscribe(
            @PathVariable(name = "subId") Long subId
    ){
        subscribeService.unsubscribe(subId);
        return null;
    }

}
