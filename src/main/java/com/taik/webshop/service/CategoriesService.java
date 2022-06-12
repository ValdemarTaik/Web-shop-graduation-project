package com.taik.webshop.service;

import com.taik.webshop.dto.CategoriesDto;
import com.taik.webshop.dto.ProductDto;

import java.util.List;

public interface CategoriesService {
    List<CategoriesDto> getAll();
}
