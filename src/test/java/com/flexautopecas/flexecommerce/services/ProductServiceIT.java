package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.repositories.ProductRepository;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceIT {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repository;

    private Long existingId, nonExistingId, countProducts;

    @BeforeEach
    public void setup(){
        existingId = 1L;
        nonExistingId = 500L;
        countProducts = 1L;
    }
    @Test
    public void deleteShouldDeleteProductWhenIdExists(){
        service.delete(existingId);

        Assertions.assertEquals(countProducts - 1, repository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
    }

}
