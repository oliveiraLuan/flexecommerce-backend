package com.flexautopecas.flexecommerce.repositories;

import com.flexautopecas.flexecommerce.entities.Product;
import com.flexautopecas.flexecommerce.tests.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;
    private Long existingId;
    @BeforeEach
    void setup(){
        existingId = 1L;
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
}
