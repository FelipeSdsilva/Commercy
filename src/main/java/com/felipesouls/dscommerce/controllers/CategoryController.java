package com.felipesouls.dscommerce.controllers;

import com.felipesouls.dscommerce.dto.CategoryDTO;
import com.felipesouls.dscommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getCategoryPerPaged(Pageable pageable) {
        return ResponseEntity.ok(categoryService.allcategoriesPaginated(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getCategoryPerId(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findCategoryPerId(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> postNewCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryDTO1 = categoryService.insertNewCategory(categoryDTO);
        var uri = fromCurrentRequest().path("/{id}").buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> putCategoryPerId(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategoryPerId(id, categoryDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategoryPerId(@PathVariable Long id) {
        categoryService.deleteCategoryPerId(id);
        return ResponseEntity.noContent().build();
    }
}
