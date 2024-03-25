package com.felipesouls.dscommerce.repositories;

import com.felipesouls.dscommerce.entities.User;
import com.felipesouls.dscommerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(name = "searchUserAndRolesByEmail")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
