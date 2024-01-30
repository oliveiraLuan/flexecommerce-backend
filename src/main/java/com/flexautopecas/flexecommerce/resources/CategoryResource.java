package com.flexautopecas.flexecommerce.resources;

import com.flexautopecas.flexecommerce.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Veículos"));
        categories.add(new Category(2L, "Pneus"));
        categories.add(new Category(3L, "Carros"));

        return ResponseEntity.ok(categories);
    }
}
