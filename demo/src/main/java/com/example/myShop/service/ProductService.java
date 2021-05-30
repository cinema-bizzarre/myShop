package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findPage(int page, int pageSize) {
        return productRepository.findAllBy(PageRequest.of(page, pageSize));
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        Category category = categoryService.findByName(productDto.getCategoryName()).orElseThrow(() ->
                new ResourceNotFoundException("Такая категория не существует = " +
                        productDto.getCategoryName() + " (Product creation)"));
        product.setCategory(category);
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Продукт с таким id не существует: " +
                        productDto.getId() + " (for update)"));
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        Category category = categoryService.findByName(productDto.getCategoryName()).orElseThrow(() ->
                new ResourceNotFoundException("Такая категория не существует = " +
                        productDto.getCategoryName() + " (Product creation)"));
        product.setCategory(category);
        return new ProductDto(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
