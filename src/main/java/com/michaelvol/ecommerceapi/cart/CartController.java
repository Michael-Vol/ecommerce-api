package com.michaelvol.ecommerceapi.cart;


import com.michaelvol.ecommerceapi.cart.dto.AddToCartRequest;
import com.michaelvol.ecommerceapi.cart.dto.AddToCartResponse;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.product.Product;
import com.michaelvol.ecommerceapi.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL  +  "/carts")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(AddToCartRequest request) {
        //Get authenticated user from security context
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null)
            throw new BadRequestException("User must be authenticated to add to cart");
        Long cartId = user.getCart().getId();
        cartService.addItemToCart(cartId, request.getProductId(), request.getQuantity());

        AddToCartResponse response = AddToCartResponse
                .builder()
                .cartId(cartId)
                .message("Product with id " + request.getProductId() + " added to cart successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
