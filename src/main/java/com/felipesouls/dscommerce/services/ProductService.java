package com.felipesouls.dscommerce.services;

import com.felipesouls.dscommerce.dto.CategoryDTO;
import com.felipesouls.dscommerce.dto.ProductDTO;
import com.felipesouls.dscommerce.entities.Category;
import com.felipesouls.dscommerce.entities.Product;
import com.felipesouls.dscommerce.mapper.ProductMapper;
import com.felipesouls.dscommerce.records.ProductMinRecord;
import com.felipesouls.dscommerce.repositories.CategoryRepository;
import com.felipesouls.dscommerce.repositories.ProductRepository;
import com.felipesouls.dscommerce.services.exceptions.DatabaseException;
import com.felipesouls.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ProductMapper productMapper = new ProductMapper();

    @Transactional(readOnly = true)
    public Page<ProductDTO> allProductsPaginated(Pageable pageable) {
        return productRepository.findAllProductsWithCategories(pageable).map(product -> new ProductDTO(product, product.getCategories()));
    }

    @Transactional(readOnly = true)
    public Page<ProductMinRecord> retrieverAllProductResumedPaginated(String name, Pageable pageable) {
        return productRepository.paginationAllProductsAndSearchPerName(name, pageable).map(product -> new ProductMinRecord(product.getId(), product.getName(), product.getPrice(), product.getImgUrl()));
    }

    @Transactional(readOnly = true)
    public ProductDTO findProductPerId(Long id) {
        return new ProductDTO(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Id not found")), productRepository.findById(id).get().getCategories());
    }

    @Transactional
    public ProductDTO insertNewProduct(ProductDTO productDTO) {
        Product product = new Product();
        product = productMapper.toEntity(productDTO, Product.class);
        insertAndUpdateCategoriesInProduct(productDTO, product);
        product = productRepository.save(product);
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO updateProductPerId(Long id, ProductDTO productDTO) {
        try {
            Product product = new Product();
            product = productRepository.getReferenceById(id);
            productDTO.setId(product.getId());
            productMapper.updateFromDto(productDTO, product);
            insertAndUpdateCategoriesInProduct(productDTO, product);
            productRepository.save(product);
            return new ProductDTO(product, product.getCategories());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id: " + id + " not found!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteProductPerId(Long id) {
        if (!productRepository.existsById(id))
            throw new ResourceNotFoundException("Id: " + id + " not found!");
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violation database!");
        }
    }

    private void insertAndUpdateCategoriesInProduct(ProductDTO productDTO, Product product) {
        product.getCategories().clear();
        for (CategoryDTO categoryDTO : productDTO.getCategories()) {
            Category category = categoryRepository.getReferenceById(categoryDTO.getId());
            product.getCategories().add(category);
        }
    }
}
