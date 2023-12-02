package com.felipesouls.dscommerce.repositories;

import com.felipesouls.dscommerce.entities.OrderItem;
import com.felipesouls.dscommerce.entities.pks.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
