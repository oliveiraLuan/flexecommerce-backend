package com.flexautopecas.flexecommerce.entities;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
    private Long id;
    private String name;
    public Category(){

    }
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
