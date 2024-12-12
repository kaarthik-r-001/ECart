package com.assign1.demo.services;

import com.assign1.demo.entity.Product;
import com.assign1.demo.entity.ProductRequestDTO;
import com.assign1.demo.entity.ProductResponseDTO;
import com.assign1.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.modelmapper.ModelMapper;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ProductService {

    private final ProductRepository productRepository;


    private ModelMapper modelMapper;


    /**
     * Retrieves a list of products based on optional filters.
     *
     * @param id the ID of the product to retrieve (optional).
     * @return a list of products matching the specified filters.
     */
    public List<Product> getProducts(Integer id) {
        if (id != null) {
            Optional<Product> productOptional = productRepository.findById(id);
            return productOptional.isPresent() ?
                    Collections.singletonList(productOptional.get()) :
                    Collections.emptyList();
        }
        return productRepository.findAll();
    }

    /**
     * Creates a new product with the provided product details.
     *
     * @param productRequestDTO the details of the product to create.
     * @return the created product's data transfer object (DTO).
     */
    @Transactional
    public ProductResponseDTO createProduct(@Valid ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);

        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductResponseDTO.class);
    }

    /**
     * Updates an existing product with the provided product details.
     *
     * @param productRequestDTO the details of the product to update.
     * @return the updated product's data transfer object (DTO).
     */
    @Transactional
    public ProductResponseDTO updateProduct(@Valid ProductRequestDTO productRequestDTO) {
        Optional<Product> existingProduct = productRepository.findById(productRequestDTO.getProductId());

        Product product = existingProduct.orElseThrow(() -> new RuntimeException("Product not found"));

        modelMapper.map(productRequestDTO, product);


        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductResponseDTO.class);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     */
    @Transactional
    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

}
