package com.assign1.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    int productId;
    @Column(name="product_name",unique = true,nullable = false)
    String productName;
    @Column(name="quantity",nullable = false)
    int quantity;
    @Column(name="brand_name",nullable = false)
    String brandName;

    public Product(String productName, int quantity, String brandName) {
        this.productName = productName;
        this.quantity = quantity;
        this.brandName = brandName;
    }
}
