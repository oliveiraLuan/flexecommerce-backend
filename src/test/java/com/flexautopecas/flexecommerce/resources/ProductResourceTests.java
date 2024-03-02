package com.flexautopecas.flexecommerce.resources;

import com.flexautopecas.flexecommerce.dto.ProductDTO;
import com.flexautopecas.flexecommerce.services.ProductService;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import com.flexautopecas.flexecommerce.tests.ProductFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductResource.class)
public class ProductResourceTests {

    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;

    private ProductDTO productDTO;

    private PageImpl<ProductDTO> page;

    private Long existingId;

    private Long nonExistingId;

    @BeforeEach
    public void setup(){
        existingId = 1L;
        nonExistingId = 350L;
        productDTO = ProductFactory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));
        when(productService.findAllPaged(any())).thenReturn(page);
        when(productService.findByID(existingId)).thenReturn(productDTO);
        when(productService.findByID(nonExistingId)).thenThrow(ResourceNotFoundException.class);
    }

    @Test
    public void findAllShouldReturnPage() throws Exception {
        ResultActions result = mockMvc
                        .perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test

    public void findByIdShouldReturnProductWhenIdExists() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/products/{id}", existingId).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/products/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }
}
