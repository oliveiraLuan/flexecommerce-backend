package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.repositories.ProductRepository;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Long nonExistingId, dependentId, existingId;

    @BeforeEach
    void setup(){
        nonExistingId = 33L;
        dependentId = 1L;
        existingId = 2L;
        when(productRepository.existsById(existingId)).thenReturn(true);
        when(productRepository.existsById(nonExistingId)).thenReturn(false);
        when(productRepository.existsById(dependentId)).thenReturn(true);
    }
    @Test
    public void deleteByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });
    }



}
