package com.shivam.blog.application.controller;


import com.shivam.blog.application.models.Category;
import com.shivam.blog.application.paylods.ApiResponse;
import com.shivam.blog.application.paylods.CategoryDto;

import com.shivam.blog.application.services.CategoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }
    @PutMapping("/{catId}")
    private ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
        CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    private ResponseEntity<ApiResponse> deleteCategory (@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted", true),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    private ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = this.categoryService.getCategoryById(catId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
    @GetMapping("/")
    private ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtos = this.categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }
}

