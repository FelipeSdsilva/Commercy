package com.felipesouls.dscommerce.services;

import com.felipesouls.dscommerce.dto.CategoryDTO;
import com.felipesouls.dscommerce.entities.Category;
import com.felipesouls.dscommerce.repositories.CategoryRepository;
import com.felipesouls.dscommerce.services.exceptions.DatabaseException;
import com.felipesouls.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> allcategoriesPaginated(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDTO::new);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findCategoryPerId(Long id) {
        return new CategoryDTO(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Id not found")));
    }

    @Transactional
    public CategoryDTO insertNewCategory(CategoryDTO categoryDTO) {
        return new CategoryDTO(categoryRepository.save(new Category(categoryDTO)));
    }

    @Transactional
    public CategoryDTO updateCategoryPerId(Long id, CategoryDTO categoryDTO) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            category.setName(category.getName());
            categoryRepository.save(category);
            return new CategoryDTO(category);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id + " Id not found");
        }
    }

    public void deleteCategoryPerId(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("");
        }
    }
}
