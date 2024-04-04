package com.felipesouls.dscommerce.records;

import java.util.Set;

public record OrderItemRecord(
        Set<ItemMinRecord> items
) {
}
