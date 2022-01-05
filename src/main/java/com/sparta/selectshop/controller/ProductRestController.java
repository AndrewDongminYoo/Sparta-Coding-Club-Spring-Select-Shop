package com.sparta.selectshop.controller;

import com.sparta.selectshop.dto.ProductMypriceRequestDto;
import com.sparta.selectshop.dto.ProductRequestDto;
import com.sparta.selectshop.models.Product;
import com.sparta.selectshop.models.ProductRepository;
import com.sparta.selectshop.security.UserDetailsImpl;
import com.sparta.selectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("/api/products")
    public List<Product> readProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        Long userId = userDetails.getUser().getId();
        // 응답 보내기
        return productService.createProduct(requestDto, userId);
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.update(id, requestDto);
    }
}
