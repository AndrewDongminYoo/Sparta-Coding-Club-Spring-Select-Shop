package com.sparta.selectshop.models;

import com.sparta.selectshop.dto.ItemDto;
import com.sparta.selectshop.dto.ProductMypriceRequestDto;
import com.sparta.selectshop.dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name="Product")
@NoArgsConstructor
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lowPrice;

    @Column(nullable = false)
    private int myPrice;

    @Column(nullable = false)
    private Long userId;

    // 관심 상품 생성 시 이용합니다.
    public Product(ProductRequestDto requestDto, Long userId) {
        // 관심상품을 등록한 회원 Id 저장
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lowPrice = requestDto.getLowPrice();
        this.myPrice = 0;
    }

    public void update(ProductMypriceRequestDto requestDto) {
        this.myPrice = requestDto.getMyPrice();
    }

    public void updateByItemDto(ItemDto itemDto) {
        this.title = itemDto.getTitle();
        this.image = itemDto.getImage();
        this.link = itemDto.getLink();
        this.lowPrice = itemDto.getLowPrice();
    }
}
