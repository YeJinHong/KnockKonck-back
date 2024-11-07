package com.test.knockknockback.api.item;


import com.test.knockknockback.api.item.dto.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping()
    public List<ItemResponseDTO> getItemList(
            @RequestParam(name = "itemNumberList") List<String> itemNumberList
    ){
        return itemService.getItemInfoList(itemNumberList);
    }
}
