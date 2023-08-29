package com.michaelvol.ecommerceapi.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/products")
public class ProductController {

    @PostMapping("/new")
    public ResponseEntity<CreateProductResponse> createProduct(CreateProductRequest createProductRequest) {

    }
}
