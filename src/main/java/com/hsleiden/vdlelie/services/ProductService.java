package com.hsleiden.vdlelie.services;


import com.hsleiden.vdlelie.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService
{
    Optional<Product> findById(String id);
    Product save(Product product);
    List<Product> findAll();
    void delete(Product product);
}
