package com.assign1.demo.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDTO {

    int productId;
    @NotNull
    @NotEmpty
    String productName;
    @NotNull
    int quantity;
    @NotNull
    @NotEmpty
    String brandName;

    public ProductResponseDTO(String productName, int quantity, String brandName) {
        this.productName = productName;
        this.quantity = quantity;
        this.brandName = brandName;
    }
}
