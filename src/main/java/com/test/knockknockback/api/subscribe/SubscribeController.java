package com.test.knockknockback.api.subscribe;

import com.test.knockknockback.api.subscribe.dto.SubscribeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<SubscribeResponseDTO> getSubscribeList(
            @RequestParam String userName
    ){
        return subscribeService.getSubscribeList(userName);
    }

}
