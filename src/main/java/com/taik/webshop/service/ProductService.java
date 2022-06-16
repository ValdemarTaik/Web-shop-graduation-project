package com.taik.webshop.service;

import com.taik.webshop.domain.Category;
import com.taik.webshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    void addToUserBucket(Long productId, String username);
    void deleteFromUserBucketProduct(Long productId, String username);
    ProductDto getById(Long id);

    List<ProductDto> getProductByCategory(Category category);
}
