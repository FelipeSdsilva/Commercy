package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.entities.Order;
import com.felipesouls.dscommerce.entities.OrderItem;
import com.felipesouls.dscommerce.entities.enums.OrderStatus;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private UserDTO client;
    private PaymentDTO payment;

    private Set<OrderItemDTO> items = new HashSet<>();

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, UserDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order order) {
        BeanUtils.copyProperties(order, this);
        client = new UserDTO(order.getClient());
        payment = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());
    }

    public OrderDTO(Order order, Set<OrderItem> orderItems) {
        this(order);
        orderItems.forEach(items -> this.items.add(new OrderItemDTO(items)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserDTO getClient() {
        return client;
    }

    public void setClient(UserDTO client) {
        this.client = client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public Set<OrderItemDTO> getItems() {
        return items;
    }

    public Double getTotalOrder() {
        return getItems().stream().mapToDouble(OrderItemDTO::getSubTotal).sum();
    }
}
