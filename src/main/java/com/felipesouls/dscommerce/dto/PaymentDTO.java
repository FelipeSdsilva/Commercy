package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.entities.Payment;
import org.springframework.beans.BeanUtils;

import java.time.Instant;


public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Instant moment, OrderDTO order) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment payment) {
        BeanUtils.copyProperties(payment, this);
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
}
