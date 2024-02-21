package com.flexautopecas.flexecommerce.repositories;

import com.flexautopecas.flexecommerce.entities.Product;
import com.flexautopecas.flexecommerce.tests.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;
    private Long existingId;

    private Long nonExistingId;
    @BeforeEach
    void setup(){
        existingId = 1L;
        nonExistingId = 33L;
    }

    @Test
    public void deleteShouldDeleteProductWhenIdExists(){
        repository.deleteById(existingId);

        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void insertShouldSaveProductWhenIdNull(){
        Product product = ProductFactory.createProduct();
        product.setId(null);

        product = repository.save(product);
        Assertions.assertNotNull(product.getId());
    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists(){
        Optional<Product> optionalProduct = repository.findById(1L);
        Assertions.assertTrue(optionalProduct.isPresent());
    }

    @Test
    public void findByIdShouldNotReturnProductWhenIdNotExists(){
        Optional<Product> optionalProduct = repository.findById(nonExistingId);
        Assertions.assertFalse(optionalProduct.isPresent());
    }
}
