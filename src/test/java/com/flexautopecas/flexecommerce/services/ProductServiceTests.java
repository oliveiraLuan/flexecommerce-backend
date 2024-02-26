package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.entities.Product;
import com.flexautopecas.flexecommerce.repositories.ProductRepository;
import com.flexautopecas.flexecommerce.services.exceptions.DatabaseException;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import com.flexautopecas.flexecommerce.tests.ProductFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Long nonExistingId, dependentId, existingId;

    private PageImpl<Product> page;

    @BeforeEach
    void setup(){
        nonExistingId = 33L;
        dependentId = 1L;
        existingId = 2L;


        page = new PageImpl<>(List.of(ProductFactory.createProduct()));

        when(productRepository.existsById(existingId)).thenReturn(true);
        when(productRepository.existsById(nonExistingId)).thenReturn(false);
        when(productRepository.existsById(dependentId)).thenReturn(true);

        when(productRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

        when(productRepository.save(ArgumentMatchers.any())).thenReturn(ProductFactory.createProduct());

        when(productRepository.findById(existingId)).thenReturn(Optional.of(ProductFactory.createProduct()));

        when(productRepository.findById(nonExistingId)).thenReturn(Optional.empty());



        doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(dependentId);
    }
    @Test
    public void deleteByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });
    }

    @Test
    public void deleteByIdShouldDoNothingWhenIdExists(){
        assertDoesNotThrow(() -> {
            productService.delete(existingId);
        });
    }

    @Test
    public void deleteByIdShouldThrowDatabaseExceptionWhenDependentId(){
        assertThrows(DatabaseException.class, () -> {
           productService.delete(dependentId);
        });
    }
}
