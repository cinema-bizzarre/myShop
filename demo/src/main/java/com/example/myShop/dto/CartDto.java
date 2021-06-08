package com.example.demo.dto;

import com.example.demo.util.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private List<ProductDto> cartDtoItems;

    public CartDto(Cart cart) {
        this.cartDtoItems = cart.getItems().stream().map(ProductDto::new).collect(Collectors.toList());
    }
    @PostConstruct
    public void init() {
        cartDtoItems = new ArrayList<>();
    }

}

