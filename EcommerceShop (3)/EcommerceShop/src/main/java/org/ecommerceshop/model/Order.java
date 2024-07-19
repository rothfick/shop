package org.ecommerceshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Order date is mandatory")
    private LocalDateTime orderDate;

    @NotNull(message = "User is mandatory")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    private List<Product> products;

    private BigDecimal total;

    public Order() {}

    public Order(LocalDateTime orderDate, User user) {
        this.orderDate = orderDate;
        this.user = user;
    }
}
