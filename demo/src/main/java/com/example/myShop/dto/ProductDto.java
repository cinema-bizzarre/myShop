package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @Size(min = 4, max = 255, message = "Name size: 4-255")
    private String name;

    @Min(value = 1, message = "Min price = 1")
    private int price;

    @NotEmpty(message = "Категория не может быть пустой")
    private String categoryName;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryName = product.getCategory().getName();
    }
}
