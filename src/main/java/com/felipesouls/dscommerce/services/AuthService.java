package com.felipesouls.dscommerce.services;

import com.felipesouls.dscommerce.entities.User;
import com.felipesouls.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(long userId) {
        User me = userService.authenticate();
        if (!me.hasRole("ROLE_AGMIN") && me.getId().equals(userId))
            throw new ForbiddenException("Access denied!");
    }
}
