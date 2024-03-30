package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.entities.User;

public class UserMinDTO {

    private Long id;
    private String name;

    public UserMinDTO() {
    }

    public UserMinDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserMinDTO(User user) {
        id = user.getId();
        name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
