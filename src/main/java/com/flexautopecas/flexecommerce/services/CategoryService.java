package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.dto.CategoryDTO;
import com.flexautopecas.flexecommerce.entities.Category;
import com.flexautopecas.flexecommerce.repositories.CategoryRepository;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> categories = repository.findAll();
        return categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findByID(Long id){
        Optional<Category> optional = repository.findById(id);
        Category category = optional.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDTO(category);
    }
}
