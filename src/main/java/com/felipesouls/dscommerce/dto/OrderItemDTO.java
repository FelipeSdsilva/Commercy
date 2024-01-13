package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.dto.pks.OrderItemPKDTO;
import com.felipesouls.dscommerce.entities.OrderItem;

public class OrderItemDTO {

    private OrderItemPKDTO id = new OrderItemPKDTO();
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderDTO order, ProductDTO product, Integer quantity, Double price) {
        id.setOrderDTO(order);
        id.setProductDTO(product);
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemDTO(OrderItem items) {
        setProductDTO(new ProductDTO(items.getProduct()));
        quantity = items.getQuantity();
        price = items.getPrice();
    }

    public OrderDTO getOrder() {
        return id.getOrderDTO();
    }

    public void setOrder(OrderDTO order) {
        id.setOrderDTO(order);
    }

    public ProductDTO getProduct() {
        return id.getProductDTO();
    }

    public void setProductDTO(ProductDTO productDTO) {
        id.setProductDTO(productDTO);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSubTotal() {
        return this.price * this.quantity;
    }
}