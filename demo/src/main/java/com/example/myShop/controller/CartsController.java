package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import com.example.demo.util.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartsController {
    private final Cart cart;
    private final ProductService productService;


    @GetMapping()
    public List<ProductDto> getCartDto() {
        CartDto cartDto=new CartDto(cart);
        return cartDto.getCartDtoItems();
    }

    @GetMapping("/add")
    public int add(@RequestParam Long id) {
        log.info("добавить в корзину продукт с id=" + id);
        return cart.addItem(productService.findById(id).get());
    }

    @GetMapping("/delete")
    public int deleteProductFromCart(@RequestParam Long id) {
        log.info("удалить из корзины продукт с id=" + id);
        return cart.deleteItem(id);
    }

    @GetMapping("/summ")
    public int summCart() {
        return cart.sumItems();
    }
}
