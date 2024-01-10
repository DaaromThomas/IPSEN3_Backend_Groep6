package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.model.Customer;
import com.hsleiden.vdlelie.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(String id);
    Optional<Product> findByProductNumber(int productnumber);
    Product save(Product product);
    List<Product> findAll();
    void delete(Product product);
    int setIsPackedForProduct(boolean isPacked, int productNumber);
    List<Product> findUnpackedProductsByCustomer(Customer customer);
}
