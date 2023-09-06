package com.michaelvol.ecommerceapi.cart;


import com.michaelvol.ecommerceapi.cart.dto.*;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.order.Order;
import com.michaelvol.ecommerceapi.user.User;
import com.michaelvol.ecommerceapi.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/carts")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(@Valid @RequestBody AddToCartRequest request) {
        Long cartId = getCartIdFromAuthenticatedUser();
        cartService.addItemToCart(cartId, request.getProductId(), request.getQuantity());

        AddToCartResponse response = AddToCartResponse
                .builder()
                .cartId(cartId)
                .message("Product with id " + request.getProductId() + " added to cart successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<GetCartResponse> getCart() {
        Long cartId = getCartIdFromAuthenticatedUser();
        Cart cart = cartService.getCartById(cartId);
        GetCartResponse response = GetCartResponse
                .builder()
                .cart(cart)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/removeFromCart")
    public ResponseEntity<RemoveFromCartResponse> removeFromCart(@Valid @RequestBody RemoveFromCartRequest request) {
        Long cartId = getCartIdFromAuthenticatedUser();
        cartService.removeItemFromCart(cartId, request.getProductId());
        RemoveFromCartResponse response = RemoveFromCartResponse
                .builder()
                .productId(request.getProductId())
                .message("Product with id " + request.getProductId() + " removed from cart successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateQuantity")
    public ResponseEntity<UpdateQuantityResponse> updateQuantity(@Valid @RequestBody UpdateQuantityRequest request) {
        Long cartId = getCartIdFromAuthenticatedUser();
        cartService.updateItemQuantity(cartId, request.getProductId(), request.getQuantity());
        UpdateQuantityResponse response = UpdateQuantityResponse
                .builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .message("Product with id " + request.getProductId() + " quantity updated successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ClearCartResponse> clearCart() {
        Long cartId = getCartIdFromAuthenticatedUser();
        cartService.clearCart(cartId);
        ClearCartResponse response = ClearCartResponse
                .builder()
                .message("Cart cleared successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<CartCheckoutResponse> checkout(@Valid @RequestBody CartToOrderRequest request) {
        getCartIdFromAuthenticatedUser();
        Order order = cartService.checkout(request);
        CartCheckoutResponse response = CartCheckoutResponse
                .builder()
                .order(order)
                .message("Order placed successfully. Your order id is " + order.getId() + ". You will receive an " +
                        "email confirmation shortly.")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Long getCartIdFromAuthenticatedUser() throws BadRequestException {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(userEmail);
        if (user == null)
            throw new BadRequestException("User must be authenticated to add to cart");
        return user.getCart().getId();
    }

}
