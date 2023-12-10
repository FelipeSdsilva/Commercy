package com.felipesouls.dscommerce.dto;

import com.felipesouls.dscommerce.entities.Category;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;


public class CategoryDTO {

    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private final Set<ProductDTO> products = new HashSet<>();

    public CategoryDTO(Category category) {
        BeanUtils.copyProperties(category, this);
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

    public Set<ProductDTO> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO category)) return false;

        return getId() != null ? getId().equals(category.getId()) : category.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
