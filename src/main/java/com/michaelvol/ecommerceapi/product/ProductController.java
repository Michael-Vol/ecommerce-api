package com.michaelvol.ecommerceapi.product;

import com.michaelvol.ecommerceapi.product.dto.*;
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

    @PostMapping("")
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
    public ResponseEntity<Iterable<Product>> getAllProducts(@Valid PageableProductQuery query) {
        Iterable<Product> products = productService.findAll(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> searchProducts(@Valid ProductSearchQuery query) {
        Iterable<Product> products = productService.search(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        DeleteProductResponse response = DeleteProductResponse
                .builder()
                .id(id)
                .message("Product with id " + id + " deleted successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable Long id,
                                                               @Valid @RequestBody UpdateProductRequest request) {
        Product product = productService.findById(id);

        Product updatedProduct = productService.update(request, product);
        UpdateProductResponse response = UpdateProductResponse
                .builder()
                .product(updatedProduct)
                .message("Product with id " + id + " updated successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
