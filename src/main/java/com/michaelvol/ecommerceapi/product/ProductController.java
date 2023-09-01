package com.michaelvol.ecommerceapi.product;

import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;
import com.michaelvol.ecommerceapi.product.dto.CreateProductResponse;
import com.michaelvol.ecommerceapi.product.dto.ProductSearchQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<CreateProductResponse> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        Product product = productService.create(createProductRequest);
        CreateProductResponse response = CreateProductResponse
                .builder()
                .id(product.getId())
                .title(product.getTitle())
                .message("Product " + product.getTitle() + " created successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new BadRequestException("Product with id " + id + " not found"));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<Iterable<Product>> searchProducts(@RequestParam ProductSearchQuery query) {
        Iterable<Product> products = productService.search(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
