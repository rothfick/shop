package org.ecommerceshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ecommerceshop.model.Order;
import org.ecommerceshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new order")
    public Order addOrder(@Valid @RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order by ID")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/purchase")
    @Operation(summary = "Purchase an order")
    public ResponseEntity<Order> purchaseOrder(@Valid @RequestBody Order order) {
        Order newOrder = orderService.purchaseOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
