package com.felipesouls.dscommerce.repositories;

import com.felipesouls.dscommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
