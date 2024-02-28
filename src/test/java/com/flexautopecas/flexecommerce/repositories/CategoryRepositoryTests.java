package com.flexautopecas.flexecommerce.repositories;

import com.flexautopecas.flexecommerce.entities.Category;
import com.flexautopecas.flexecommerce.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void deleteShouldDeleteCategoryWhenIdExists(){
        categoryRepository.deleteById(1L);
        Optional<Category> result = categoryRepository.findById(1L);
        Assertions.assertFalse(result.isPresent());
    }
}
