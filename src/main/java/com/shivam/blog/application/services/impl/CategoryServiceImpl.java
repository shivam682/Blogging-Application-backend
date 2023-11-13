package com.shivam.blog.application.services.impl;

import com.shivam.blog.application.exceptions.ResourceNotFoundException;
import com.shivam.blog.application.models.Category;
import com.shivam.blog.application.models.User;
import com.shivam.blog.application.paylods.CategoryDto;
import com.shivam.blog.application.repositries.CategoryRepo;
import com.shivam.blog.application.repositries.UserRepo;
import com.shivam.blog.application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        this.categoryRepo.save(category);
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        Category category =this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Id",catId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory=this.categoryRepo.save(category);
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        Category category =this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Id", catId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category category =this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Id", catId));
        this.categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
        return categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
    }
}
