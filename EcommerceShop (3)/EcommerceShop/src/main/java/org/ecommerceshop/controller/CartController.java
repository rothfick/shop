package org.ecommerceshop.controller;

import org.ecommerceshop.model.Cart;
import org.ecommerceshop.model.CartItemRequest;
import org.ecommerceshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public Cart getCart() {
        return cartService.getCart();
    }

    @PostMapping("/add")
    public Cart addItemToCart(@RequestBody CartItemRequest cartItemRequest) {
        return cartService.addItemToCart(cartItemRequest);
    }

    @PostMapping("/remove")
    public Cart removeItemFromCart(@RequestBody CartItemRequest cartItemRequest) {
        return cartService.removeItemFromCart(cartItemRequest);
    }

    @PostMapping("/update")
    public Cart updateCartItem(@RequestBody CartItemRequest cartItemRequest) {
        return cartService.updateCartItem(cartItemRequest);
    }
}
