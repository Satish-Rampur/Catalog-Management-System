package com.example.catalog_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory") // only be applied to strings
    private String name;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    private String description;

    @NotNull(message = "Price is mandatory") // can be applied to any type
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")// specify the minimum value for a numeric field
    private BigDecimal price;

    private int quantity;

    private String category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date dateAdded;

    // Getters and Setters
}

