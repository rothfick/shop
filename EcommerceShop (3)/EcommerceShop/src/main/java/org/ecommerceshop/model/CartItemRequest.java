package org.ecommerceshop.model;

public class CartItemRequest {

    private Long productId;
    private int quantity;

    // Constructors, getters, and setters

    public CartItemRequest() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
