package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.dto.ProductDTO;
import com.flexautopecas.flexecommerce.entities.Product;
import com.flexautopecas.flexecommerce.repositories.ProductRepository;
import com.flexautopecas.flexecommerce.services.exceptions.DatabaseException;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> products = repository.findAll(pageRequest);
        return products.map(product -> new ProductDTO(product));
    }

    @Transactional(readOnly = true)
    public ProductDTO findByID(Long id){
        Optional<Product> optional = repository.findById(id);
        Product product = optional.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        product = repository.save(product);
        return new ProductDTO(product);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO product) {
        try{
            Product entity = repository.getReferenceById(id);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Produto com id informado não foi encontrado");
        }
    }

    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Produto com id informado não foi encontrado");
        } try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro de integridade referencial");
        }
    }
}
