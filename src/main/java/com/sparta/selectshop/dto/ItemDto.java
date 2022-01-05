package com.sparta.selectshop.dto;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@ToString
public class ItemDto {
    private final String title;
    private final String link;
    private final String image;
    private final int lowPrice;

    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.image = itemJson.getString("image");
        this.link = itemJson.getString("link");
        this.lowPrice = itemJson.getInt("lowPrice");
    }
}
