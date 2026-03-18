package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
