package com.sparta.selectshop.controller;

import com.sparta.selectshop.dto.ItemDto;
import com.sparta.selectshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public void searchProduct(@RequestParam String query) {
        String result = naverShopSearch.search(query);
        List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(result);
    }
}
