package com.example.catalog_management.controller;

import com.example.catalog_management.entity.Product;
import com.example.catalog_management.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(productList);

        this.mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        when(productService.getProductById(1L)).thenReturn(product);

        this.mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId()));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        this.mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Product Name\", \"brand\": \"Brand Name\", \"description\": \"Product Description\", \"price\": 100.0, \"quantity\": 10, \"category\": \"Category\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(product.getName()));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        when(productService.updateProduct(any(Long.class), any(Product.class))).thenReturn(product);

        this.mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Product Name\", \"brand\": \"Updated Brand Name\", \"description\": \"Updated Product Description\", \"price\": 150.0, \"quantity\": 15, \"category\": \"Updated Category\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(product.getName()));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        this.mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchProducts() throws Exception {
        Product product = new Product();
        List<Product> productList = Arrays.asList(product);
        when(productService.searchProducts("test", null, null)).thenReturn(productList);

        this.mockMvc.perform(get("/api/products/search?name=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
