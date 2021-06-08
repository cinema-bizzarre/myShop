package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Category getOneCategoryById(@PathVariable Long id) {
        return categoryService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Категория не найдена: " + id));
    }
}

