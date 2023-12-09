package com.felipesouls.dscommerce.services;

import com.felipesouls.dscommerce.dto.ProductDTO;
import com.felipesouls.dscommerce.entities.Product;
import com.felipesouls.dscommerce.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> allProductsPaginated(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findProductPerId(Long id) {
        return new ProductDTO(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Id not found")));
    }

    @Transactional
    public ProductDTO insertNewProduct(ProductDTO productDTO) {
        Product product = productRepository.save(new Product(productDTO));
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO updateProductPerId(Long id, ProductDTO productDTO) {
        try {
            Product product = productRepository.getReferenceById(id);
            convertDtoInEntity(productDTO,product);
            productRepository.save(product);
            return new ProductDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id + " Id not found");
        }
    }

    public void deleteProductPerId(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("");
        }
    }

    private void convertDtoInEntity(ProductDTO productDTO, Product product){
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }
}
