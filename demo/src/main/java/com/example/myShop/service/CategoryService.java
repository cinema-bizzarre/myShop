package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
