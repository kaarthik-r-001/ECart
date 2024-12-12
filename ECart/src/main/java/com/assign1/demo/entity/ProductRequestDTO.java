package com.assign1.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {
    int productId;
    @NotEmpty
    @NotNull
    String productName;
    @NotEmpty
    @NotNull
    int quantity;
    @NotEmpty
    @NotNull
    String brandName;

    public ProductRequestDTO(String productName, int quantity, String brandName) {
        this.productName = productName;
        this.quantity = quantity;
        this.brandName = brandName;
    }
}
