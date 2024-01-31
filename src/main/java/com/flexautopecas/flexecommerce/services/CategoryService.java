package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.entities.Category;
import com.flexautopecas.flexecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }
}
