package com.michaelvol.ecommerceapi.cart.impl;

import com.michaelvol.ecommerceapi.cart.Cart;
import com.michaelvol.ecommerceapi.cart.CartRepository;
import com.michaelvol.ecommerceapi.cart.CartService;
import com.michaelvol.ecommerceapi.cart.cartitem.CartItem;
import com.michaelvol.ecommerceapi.cart.cartitem.CartItemRepository;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.product.Product;
import com.michaelvol.ecommerceapi.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Data
@Builder
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long id) throws BadRequestException {
        return cartRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Cart with id " + id + " not found")
        );
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseThrow(
                () -> new BadRequestException("Cart of user with id " + userId + " not found"));
    }

    @Override
    public Cart addItemToCart(Long cartId, Long productId, Integer quantity) throws BadRequestException {
        Cart cart = getCartById(cartId);
        Product product = productService.findById(productId).orElseThrow(
                () -> new BadRequestException("Product with id " + productId + " not found")
        );
        CartItem cartItem = CartItem
                .builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();
        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * quantity));
        cart.getItems().add(cartItem);
        cart = cartRepository.save(cart);
        cartItemRepository.save(cartItem);
        return cart;
    }

    @Override
    public Cart removeItemFromCart(Long cartId, Long productId) throws BadRequestException {
        Cart cart = getCartById(cartId);
/*
        Product product = productService.findById(productId).orElseThrow(
                () -> new BadRequestException("Product with id " + productId + " not found")
        );
*/
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cartId, productId).orElseThrow(
                () -> new BadRequestException("Product with id " + productId + " not found in cart with id " + cartId)
        );
        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProduct().getPrice() * cartItem.getQuantity()));
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cart = cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart updateItemQuantity(Long cartId, Long productId, Integer quantity) {
        Cart cart = getCartById(cartId);
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cartId, productId).orElseThrow(
                () -> new BadRequestException("Product with id " + productId + " not found in cart with id " + cartId)
        );
        Integer diffQuantity = quantity - cartItem.getQuantity();
        cart.setTotalPrice(cart.getTotalPrice() + (cartItem.getProduct().getPrice() * diffQuantity));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Cart clearCart(Long cartId) {
        Cart cart = getCartById(cartId);
        cart.setTotalPrice(0.0);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
