package com.upskill.upskill.ProductService;
import org.springframework.stereotype.Service;
import repository.ProductRepository;
import exception.ProductNotFoundException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
    }
}
