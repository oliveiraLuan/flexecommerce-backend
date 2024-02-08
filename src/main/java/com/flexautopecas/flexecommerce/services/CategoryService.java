package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.dto.CategoryDTO;
import com.flexautopecas.flexecommerce.entities.Category;
import com.flexautopecas.flexecommerce.repositories.CategoryRepository;
import com.flexautopecas.flexecommerce.services.exceptions.DatabaseException;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
        Page<Category> categories = repository.findAll(pageRequest);
        return categories.map(category -> new CategoryDTO(category));
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
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO category) {
        try{
            Category entity = repository.getReferenceById(id);
            entity.setName(category.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Categoria com id informado não foi encontrada");
        }
    }

    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Categoria com id informado não foi encontrada");
        } try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro de integridade referencial, a categoria está associada a um produto");
        }
    }
}
