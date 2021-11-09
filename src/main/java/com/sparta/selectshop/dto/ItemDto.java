package com.sparta.selectshop.dto;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class ItemDto {
    private final String title;
    private final String link;
    private final String image;
    private final int lowPrice;

    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.image = itemJson.getString("image");
        this.link = itemJson.getString("link");
        this.lowPrice = itemJson.getInt("lprice");
    }
}
