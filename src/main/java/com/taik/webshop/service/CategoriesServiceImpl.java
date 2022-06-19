package com.taik.webshop.service;

import com.taik.webshop.dao.CategoriesRepository;
import com.taik.webshop.dto.CategoriesDto;
import com.taik.webshop.mapper.CategoriesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService{
    private final CategoriesMapper mapper = CategoriesMapper.MAPPER;

   private final CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<CategoriesDto> getAll() {
        return mapper.fromCategoryList(categoriesRepository.findAll());
    }


}
