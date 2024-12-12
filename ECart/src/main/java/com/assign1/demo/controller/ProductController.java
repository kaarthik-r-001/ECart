package com.assign1.demo.controller;

import com.assign1.demo.entity.Product;
import com.assign1.demo.entity.ProductRequestDTO;
import com.assign1.demo.entity.ProductResponseDTO;
import com.assign1.demo.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * Retrieves a list of products based on optional filters.
     *
     * @param id the ID of the product to retrieve (optional).
     * @return a list of products matching the specified filters.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    public List<Product> getProducts(
            @RequestParam(required = false) Integer id) {
        List<Product> products = productService.getProducts(id);
        return products;
    }

    /**
     * Creates a new product with the provided product details.
     *
     * @param productRequestDTO the details of the product to create.
     * @return the created product.
     * @throws Exception if an error occurs during product creation.
     */
    @PreAuthorize("hasAuthority('SCOPE_WRITE')")
    @PostMapping
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) throws Exception {
        logger.info("Creating product with details: {}", productRequestDTO);
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return createdProduct;
    }

    /**
     * Updates an existing product with the provided product details.
     *
     * @param productRequestDTO the details of the product to update.
     * @return the updated product.
     * @throws Exception if an error occurs during product update.
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PutMapping
    public ProductResponseDTO updateProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) throws Exception {
        ProductResponseDTO updatedProduct = productService.updateProduct(productRequestDTO);
        return updatedProduct;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     */
    @PreAuthorize("hasAuthority('SCOPE_DELETE')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) throws Exception {
        productService.deleteProduct(id);

    }
}