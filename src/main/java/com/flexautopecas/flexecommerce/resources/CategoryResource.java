package com.flexautopecas.flexecommerce.resources;

import com.flexautopecas.flexecommerce.dto.CategoryDTO;
import com.flexautopecas.flexecommerce.entities.Category;
import com.flexautopecas.flexecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }
}
