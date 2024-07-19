package org.ecommerceshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be greater than 100 characters")
    private String name;
    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description should not be greater than 500 characters")
    private String description;
    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price should be greater than zero")
    private BigDecimal price;
    @Column(nullable = true)
    private String imageUrl;

    public Product() {}

    public Product(String name, String description, BigDecimal price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
