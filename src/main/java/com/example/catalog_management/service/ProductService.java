package com.example.catalog_management.service;

import com.example.catalog_management.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.catalog_management.entity.Product;
import com.example.catalog_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        logger.info("Adding product: {}", product);
        product.setDateAdded(new Date());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        // Fetch the existing product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // Update the fields of the existing product
        existingProduct.setName(productDetails.getName());
        existingProduct.setBrand(productDetails.getBrand());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setQuantity(productDetails.getQuantity());
        existingProduct.setCategory(productDetails.getCategory());

        // Save the updated product
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }

    public List<Product> searchProducts(String name, String brand, String category) {
        if (name != null) {
            return productRepository.findByNameContaining(name);
        } else if (brand != null) {
            return productRepository.findByBrand(brand);
        } else if (category != null) {
            return productRepository.findByCategory(category);
        } else {
            return productRepository.findAll();
        }
    }
}
