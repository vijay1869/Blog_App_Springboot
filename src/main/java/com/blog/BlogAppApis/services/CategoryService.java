package com.blog.BlogAppApis.services;

import com.blog.BlogAppApis.payloads.CategoryDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {
    //crud
    //by default public ,abstract
    CategoryDto createCategory(@Valid CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);



    void deleteCategory(Integer id);

    CategoryDto getCategory(Integer id);
    List<CategoryDto> getAllCategory();

}
