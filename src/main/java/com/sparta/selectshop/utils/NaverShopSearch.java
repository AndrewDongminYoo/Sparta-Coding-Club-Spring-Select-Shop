package com.sparta.selectshop.utils;

import com.sparta.selectshop.dto.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "a5zi0gge_84kiawuvUXG");
        headers.add("X-Naver-Client-Secret", "MYccfoJSuq");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://openapi.naver.com/v1/search/shop.json?query="+query,
                HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result) {
        JSONObject resJson = new JSONObject(result);
        JSONArray items = resJson.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (int i=0; i<items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }

    public static void main(String[] args) {
        NaverShopSearch naverShopSearch = new NaverShopSearch();
        String result = naverShopSearch.search("오리고기 동결건조 트릿");
        List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(result);
        System.out.println(itemDtoList.toString());
    }
}
