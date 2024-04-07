package com.felipesouls.dscommerce.records;

import com.felipesouls.dscommerce.entities.enums.OrderStatus;

import java.time.Instant;

public record OrderMinRecord(
        Long id,
        Instant moment,
        OrderStatus status,
        String clientName,
        Double totalOrder
) {
}
