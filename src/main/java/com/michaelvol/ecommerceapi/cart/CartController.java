package com.michaelvol.ecommerceapi.cart;


import com.michaelvol.ecommerceapi.cart.dto.*;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/carts")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(AddToCartRequest request) {
        Long cartId = getCartIdFromAuthenticatedUser();
        cartService.addItemToCart(cartId, request.getProductId(), request.getQuantity());

        AddToCartResponse response = AddToCartResponse
                .builder()
                .cartId(cartId)
                .message("Product with id " + request.getProductId() + " added to cart successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/removeFromCart")
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

    @PostMapping("/updateQuantity")
    public ResponseEntity<UpdateQuantityResponse> updateQuantity(UpdateQuantityRequest request) {
        Long cartId = getCartIdFromAuthenticatedUser();
        cartService.updateItemQuantity(cartId, request.getProductId(), request.getQuantity());
        UpdateQuantityResponse response = UpdateQuantityResponse
                .builder()
                .productId(request.getProductId())
                .message("Product with id " + request.getProductId() + " quantity updated successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private Long getCartIdFromAuthenticatedUser() throws BadRequestException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null)
            throw new BadRequestException("User must be authenticated to add to cart");
        return user.getCart().getId();
    }

}
