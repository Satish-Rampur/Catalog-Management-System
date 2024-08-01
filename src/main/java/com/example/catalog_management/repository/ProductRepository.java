package com.example.catalog_management.repository;
import com.example.catalog_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContaining(String name);
    List<Product> findByBrand(String brand);
    List<Product> findByCategory(String category);
}
