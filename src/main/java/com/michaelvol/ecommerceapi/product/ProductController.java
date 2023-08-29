package com.michaelvol.ecommerceapi.product;

import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;
import com.michaelvol.ecommerceapi.product.dto.CreateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/new")
    public ResponseEntity<CreateProductResponse> createProduct(CreateProductRequest createProductRequest) {
        Product product = productService.create(createProductRequest);
        CreateProductResponse response = CreateProductResponse
                .builder()
                .id(product.getId())
                .title(product.getTitle())
                .message("Product " + product.getTitle() + " created successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
