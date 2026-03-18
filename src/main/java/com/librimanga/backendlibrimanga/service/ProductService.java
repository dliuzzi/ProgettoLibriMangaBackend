package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.model.Product;
import com.librimanga.backendlibrimanga.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product save(Product p) {
        return repo.save(p);
    }
}
