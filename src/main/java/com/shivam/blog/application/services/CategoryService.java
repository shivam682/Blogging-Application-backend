package com.shivam.blog.application.services;

import com.shivam.blog.application.paylods.CategoryDto;
import com.shivam.blog.application.paylods.UserDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);
    CategoryDto getCategoryById(Integer catId);
    void deleteCategory(Integer catId);
    List<CategoryDto> getAllCategory();
}
