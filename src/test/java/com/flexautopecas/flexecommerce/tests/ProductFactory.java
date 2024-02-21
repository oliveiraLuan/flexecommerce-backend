package com.flexautopecas.flexecommerce.tests;

import com.flexautopecas.flexecommerce.dto.ProductDTO;
import com.flexautopecas.flexecommerce.entities.Category;
import com.flexautopecas.flexecommerce.entities.Product;

import java.time.Instant;

public class ProductFactory {
    public static Product createProduct(){
        Product product = new Product(1L, "Kit correia dentada", "Kit contendo a correia dentada e o tensor", 320.00, "", Instant.parse("2024-02-20T03:00:00Z"));
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        product.getCategories().add(new Category(1L, "Motor"));
        return new ProductDTO(product, product.getCategories());
    }
}
