package com.example.service1_ProductService.service;



import com.example.service1_ProductService.entity.Product;
import com.example.service1_ProductService.repositoy.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;


    public Product create(Product p) {
        return repo.save(p);
    }

    public Product get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product update(Long id, Product updated) {
        Product old = get(id);
        if (old == null) return null;

        old.setName(updated.getName());
        old.setPrice(updated.getPrice());
        old.setStock(updated.getStock());
        return repo.save(old);
    }

    public String delete(Long id) {
        repo.deleteById(id);
        return "Deleted Successfully";
    }

    public boolean reduceStock(Long id, int qty) {
        Product p = get(id);
        if (p != null && p.getStock() >= qty) {
            p.setStock(p.getStock() - qty);
            repo.save(p);
            return true;
        }
        return false;
    }
}

