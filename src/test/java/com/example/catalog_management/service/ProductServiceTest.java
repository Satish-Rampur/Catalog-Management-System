package com.example.catalog_management.service;


import com.example.catalog_management.entity.Product;
import com.example.catalog_management.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);
        assertNotNull(foundProduct);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(1L, product);
        assertNotNull(updatedProduct);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testSearchProducts() {
        Product product = new Product();
        when(productRepository.findByNameContaining("test")).thenReturn(Arrays.asList(product));

        List<Product> products = productService.searchProducts("test", null, null);
        assertEquals(1, products.size());
        verify(productRepository, times(1)).findByNameContaining("test");
    }
}
