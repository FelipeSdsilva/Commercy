package com.felipesouls.dscommerce.repositories;

import com.felipesouls.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
