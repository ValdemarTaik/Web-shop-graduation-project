package com.taik.webshop.dao;

import com.taik.webshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
