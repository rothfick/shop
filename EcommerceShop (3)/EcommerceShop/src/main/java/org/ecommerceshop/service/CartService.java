package org.ecommerceshop.service;

import org.ecommerceshop.model.Cart;
import org.ecommerceshop.model.CartItem;
import org.ecommerceshop.model.CartItemRequest;
import org.ecommerceshop.model.User;
import org.ecommerceshop.repository.CartRepository;
import org.ecommerceshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        OidcUser oidcUser = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(oidcUser.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Cart getCart() {
        User user = getCurrentUser();
        return cartRepository.findByUserId(user.getId()).orElse(new Cart(user));
    }

    public Cart addItemToCart(CartItemRequest cartItemRequest) {
        Cart cart = getCart();
        CartItem newItem = new CartItem(cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        cart.addItem(newItem);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(CartItemRequest cartItemRequest) {
        Cart cart = getCart();
        cart.removeItem(cartItemRequest.getProductId());
        return cartRepository.save(cart);
    }

    public Cart updateCartItem(CartItemRequest cartItemRequest) {
        Cart cart = getCart();
        cart.updateItem(cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        return cartRepository.save(cart);
    }
}
