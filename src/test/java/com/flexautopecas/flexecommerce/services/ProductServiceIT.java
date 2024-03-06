package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.dto.ProductDTO;
import com.flexautopecas.flexecommerce.repositories.ProductRepository;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
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

    @Test
    public void findAllPageShouldReturnPageWhenContentExists(){
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<ProductDTO> page = service.findAllPaged(pageRequest);

        Assertions.assertFalse(page.isEmpty());
        Assertions.assertEquals(0, page.getNumber());
        Assertions.assertEquals(10, page.getSize());
        Assertions.assertEquals(countProducts, page.getTotalElements());
    }

    @Test
    public void findAllPageShouldReturnEmptyPageWhenPageNumberDoesNotExist(){
        PageRequest pageRequest = PageRequest.of(50, 10);

        Page<ProductDTO> page = service.findAllPaged(pageRequest);

        Assertions.assertTrue(page.isEmpty());
    }

    @Test
    public void findAllPageShouldReturnSortedPageWhenSortByName(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));

        Page<ProductDTO> page = service.findAllPaged(pageRequest);

        Assertions.assertFalse(page.isEmpty());
        Assertions.assertEquals("Kit correia dentada", page.getContent().get(0).getName());
    }
}
