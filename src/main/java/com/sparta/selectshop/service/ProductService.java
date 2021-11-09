package com.sparta.selectshop.service;

import com.sparta.selectshop.dto.ItemDto;
import com.sparta.selectshop.dto.ProductMypriceRequestDto;
import com.sparta.selectshop.models.Product;
import com.sparta.selectshop.models.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void update(Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        product.update(requestDto);
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        product.updateByItemDto(itemDto);
    }
}
