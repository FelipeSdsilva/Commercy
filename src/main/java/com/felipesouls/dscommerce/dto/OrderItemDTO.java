package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.dto.pks.OrderItemPKDTO;

public class OrderItemDTO {

    private OrderItemPKDTO id = new OrderItemPKDTO();
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

    public OrderDTO getOrderDTO() {
        return id.getOrderDTO();
    }

    public void setOrderDTO(OrderDTO order) {
        id.setOrderDTO(order);
    }

    public ProductDTO getProductDTO(){
        return id.getProductDTO();
    }

    public void setProductDTO(ProductDTO productDTO){
        id.setProductDTO(productDTO);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public double getSubTotal() {
        return this.price * this.quantity;
    }
}