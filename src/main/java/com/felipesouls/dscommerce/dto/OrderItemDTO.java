package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.entities.OrderItem;

public class OrderItemDTO {

    private Long productId;
    private String name;
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long productId, Integer quantity, Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemDTO(OrderItem items) {
        productId = items.getProduct().getId();
        name = items.getProduct().getName();
        quantity = items.getQuantity();
        price = items.getProduct().getPrice();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
