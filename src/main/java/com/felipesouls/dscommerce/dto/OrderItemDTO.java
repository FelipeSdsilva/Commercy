package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.entities.Order;
import com.felipesouls.dscommerce.entities.OrderItem;
import com.felipesouls.dscommerce.entities.Product;

public class OrderItemDTO {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long orderId, Long productId, Integer quantity, Double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemDTO(OrderItem items) {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public OrderItem toOrderItem(Order order, Product product) {
        return new OrderItem(order, product, this.quantity, this.price);
    }
}
