package com.sparta.selectshop.models;

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

    public Product(ProductRequestDto productRequestDto) {
        this.title = productRequestDto.getTitle();
        this.image = productRequestDto.getImage();
        this.link = productRequestDto.getLink();
        this.lowPrice = productRequestDto.getLowPrice();
        this.myPrice = 0;
    }

    public void update(ProductMypriceRequestDto requestDto) {
        this.myPrice = requestDto.getMyPrice();
    }
}
