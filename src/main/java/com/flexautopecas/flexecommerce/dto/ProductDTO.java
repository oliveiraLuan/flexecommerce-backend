package com.flexautopecas.flexecommerce.dto;

import com.flexautopecas.flexecommerce.entities.Category;
import com.flexautopecas.flexecommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO implements Serializable {
    private Long id;

    @NotBlank(message = "{product.name-validation}")
    private String name;
    @NotBlank(message = "{product.description-validation}")
    private String description;
    @NotBlank(message = "{product.price-validation}")
    private Double price;
    @NotBlank(message = "{product.imgUrl-validation}")
    private String imgUrl;
    private Instant date;
    Set<Category> categories = new HashSet<>();

    public ProductDTO(){

    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        this.date = product.getDate();
    }

    public ProductDTO(Product product, Set<Category> categories){
        this(product);
        categories
                .forEach(category -> this.categories.add(category));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
