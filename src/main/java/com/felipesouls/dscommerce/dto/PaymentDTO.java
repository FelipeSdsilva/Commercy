package com.felipesouls.dscommerce.dto;

import java.time.Instant;


public class PaymentDTO {

    private Long id;
    private Instant moment;
    private OrderDTO order;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Instant moment, OrderDTO order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDTO payment)) return false;

        return getId() != null ? getId().equals(payment.getId()) : payment.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
