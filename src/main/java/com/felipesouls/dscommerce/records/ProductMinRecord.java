package com.felipesouls.dscommerce.records;

public record ProductMinRecord(
        Long id,
        String name,
        Double price,
        String imgUrl
) {
}
