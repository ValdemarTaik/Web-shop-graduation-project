package com.taik.webshop.mapper;


import com.taik.webshop.domain.Category;
import com.taik.webshop.domain.Product;
import com.taik.webshop.dto.CategoriesDto;
import com.taik.webshop.dto.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    CategoriesMapper MAPPER = Mappers.getMapper(CategoriesMapper.class);

    Category toCategory(CategoriesDto dto);
    Product toProduct(ProductDto dto);


    @InheritInverseConfiguration
    CategoriesDto fromCategory(Category category);
    ProductDto fromProduct(Product product);

    List<Category> toCategoryList(List<CategoriesDto> categoriesDtos);

    List<CategoriesDto> fromCategoryList(List<Category> categories);

}
