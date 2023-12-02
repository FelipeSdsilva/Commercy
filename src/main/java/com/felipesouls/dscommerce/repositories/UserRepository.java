package com.felipesouls.dscommerce.repositories;

import com.felipesouls.dscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
