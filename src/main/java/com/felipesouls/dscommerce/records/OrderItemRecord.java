package com.felipesouls.dscommerce.records;

import com.felipesouls.dscommerce.dto.OrderItemDTO;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record OrderItemRecord(

        @NotEmpty(message = "O pedido deve conter pelo menos um item!")
        Set<OrderItemDTO> items
) {
}
